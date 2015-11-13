package ru.menkin.store;

import ru.menkin.models.*;
import ru.menkin.utils.*;

import java.sql.*;
import java.util.*;

/**
 * @author Menkin
 * @since 12.10.2015
 */
public class JdbcStorage implements Storage<Player> {
    private final Connection connection;

    public JdbcStorage(){
        final Settings settings = Settings.getInstance();
        try {
            this.connection =
                    DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException("Can't create storage", e);
        }
    }

    @Override
    public Collection<Player> values() {
        final List<Player> players = new ArrayList<Player>();
        try (
                final Statement statement = this.connection.createStatement();
                final ResultSet rs = statement.executeQuery("SELECT * FROM players")) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("team"), rs.getString("name"), rs.getString("salary"), rs.getString("position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public int add(Player player) {
        try (
                final PreparedStatement statement = this.connection.prepareStatement(
                        "insert into players (team, name, salary, position) " +
                                "values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, player.getTeam());
            statement.setString(2, player.getName());
            statement.setString(3, player.getSalary());
            statement.setString(4, player.getPosition());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new player");
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player get(int id) {
        try (
                final PreparedStatement statement = this.connection.prepareStatement("select * from players where id=(?)")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new Player(rs.getInt("id"), rs.getString("team"), rs.getString("name"), rs.getString("salary"), rs.getString("position"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }

    @Override
    public void delete(int id) {
        try (
                final PreparedStatement statement = this.connection.prepareStatement("delete from players where id=(?)")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void edit(Player player) {
        try (
                final PreparedStatement statement = this.connection.prepareStatement(
                        "update players set team=(?), name=(?), salary=(?), " + "position=(?) WHERE id=(?)")) {
            statement.setString(1, player.getTeam());
            statement.setString(2, player.getName());
            statement.setString(3, player.getSalary());
            statement.setString(4, player.getPosition());
            statement.setInt(5, player.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}