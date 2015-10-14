package ru.menkin.store;

import org.junit.*;
import ru.menkin.models.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Menkin
 * @since 10.10.2015
 */
public class JdbcStorageTest {
    @Test
    public void test(){
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new Player(-1, "Arizona", "Bobby", "325000.0", "Catcher"));
        final Player player = storage.get(id);
        assertEquals(id, player.getId());

        player.setTeam("Team - Updated!");
        player.setName("Name - Updated!");
        player.setSalary("-1");
        player.setPosition("Position - Updated!");
        storage.edit(player);

        Collection<Player> collection = storage.values();
        assert collection.retainAll(storage.values());

        storage.delete(storage.add(new Player(-1, "for delete", "for delete", "0.0", "for delete")));

        storage.close();
    }
}