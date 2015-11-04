package ru.menkin.store;

import org.junit.*;
import ru.menkin.models.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Menkin
 * @since 10.10.2015
 */

public class HibernateStorageTest {
    @Ignore
    @Test
    public void testPlayerCRUD() {
        final HibernateStorage storage = new HibernateStorage();
        final int id = storage.add(new Player(1, "a", "b", "10000.0", "d"));
        List<Player> list = new ArrayList<Player>();
        list.addAll(storage.values());
        assertNotNull(id);
        assertNotNull(list);
        assertEquals(list.get(list.size() - 1).getId(), id);

        Player player = storage.get(id);
        assertEquals(player.getId(), storage.get(id).getId());

        player.setTeam("Arizona");
        storage.edit(player);

        storage.delete(storage.add(new Player(1, "delete", "delete", "0.0", "delete")));
        storage.close();
    }

    @Test
    public void createPlayerTest() {
        final HibernateStorage storage = new HibernateStorage();
        Player player = new Player(1, "Arizona", "Petrucci", "1000000.0", "Catcher");
        Player player1 = new Player(2, "Arizona", "Malmsteen", "1000000.0", "Pitcher");

        List<String> players = new ArrayList<>();
        players.add(player.getName());
        players.add(player1.getName());

        Team team = new Team(1, player.getTeam(), players);

        player.setTeam_id(team.getId());
        //not null team_id
        assertNotNull(player.getTeam_id());

        //player id = 1
        final int id = storage.add(player);
//        player = storage.get(id);
//
//        storage.edit(player);
        assertEquals(2, team.getPlayers().size());
        storage.delete(id);
        storage.close();
    }
}