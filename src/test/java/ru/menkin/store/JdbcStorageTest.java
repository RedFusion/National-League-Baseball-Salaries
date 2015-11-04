package ru.menkin.store;

import org.junit.*;
import ru.menkin.models.*;
import ru.menkin.utils.*;

import javax.naming.*;
import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Menkin
 * @since 10.10.2015
 */
public class JdbcStorageTest {
    @Test
    public void test() throws NamingException, SQLException {
        final JdbcStorage storage = new JdbcStorage();
        Player player = new Player(-1, "Arizona", "Bobby", "325000.0", "Catcher");
        final int id = storage.add(player);
        final Player p = storage.get(id);
        assertEquals(id, p.getId());

        p.setTeam("Team - Updated!");
        p.setName("Name - Updated!");
        p.setSalary("-1");
        p.setPosition("Position - Updated!");
        storage.edit(p);

        Collection<Player> collection = storage.values();
        assert collection.retainAll(storage.values());

        storage.delete(storage.add(new Player(-1, "for delete", "for delete", "0.0", "for delete")));

        storage.close();
    }
}