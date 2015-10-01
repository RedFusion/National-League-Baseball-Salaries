package ru.menkin.store;

import org.junit.Test;
import ru.menkin.models.Player;

public class HibernateStorageTest
{
    @Test
    public void testCreatePlayer()
    {
        HibernateStorage storage = new HibernateStorage();
        int id = storage.add(new Player(1, "a", "b", "10000.0", "d"));
    }
}