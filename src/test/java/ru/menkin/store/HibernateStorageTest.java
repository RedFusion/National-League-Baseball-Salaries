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
    @Test
    public void testPlayerCRUD() {
        final HibernateStorage storage = new HibernateStorage();
        final int id = storage.add(new Player(1, "a", "Bobby", "10000.0", "d"));
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
}