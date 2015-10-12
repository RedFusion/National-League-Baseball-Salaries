package ru.menkin.store;

import org.junit.*;
import ru.menkin.models.*;

import java.util.*;

import static org.junit.Assert.*;

public class JdbcStorageTest {
    @Test
    public void test(){
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new Player(-1, "Arizona", "Bobby", "325000.0", "Catcher"));
        final Player player = storage.get(id);
        assertEquals(id, player.getId());

        Collection<Player> collection = storage.values();
        assert collection.retainAll(storage.values());

        storage.close();
    }
}