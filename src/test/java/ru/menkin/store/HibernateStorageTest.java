package ru.menkin.store;

import org.junit.*;
import ru.menkin.models.*;

import java.util.*;
import static org.junit.Assert.*;

/**
 * @author Menkin
 * @since 10.10.2015
 */

public class HibernateStorageTest
{
    @Test
    public void testCreatePlayer()
    {
        HibernateStorage storage = new HibernateStorage();
        int id = storage.add(new Player(1, "a", "b", "10000.0", "d"));
        List<Player> list = new ArrayList<Player>();
        list.addAll(storage.values());
        assertNotNull(id);
        assertNotNull(list);
        assertEquals(list.get(list.size() - 1).getId(), id);
    }
}