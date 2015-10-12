package ru.menkin.store;

import org.junit.*;
import ru.menkin.models.*;

/**
 * @author Menkin
 * @since 06.10.2015
 */
public class PlayerCacheTest extends Assert {
    private final PlayerCache cache = PlayerCache.getInstance();
    @Test
    public void test(){
        Player player = new Player(1, "A", "Bob", "325000.0", "Pitcher");
        cache.add(player);
        assertEquals("Not same instances ", cache, PlayerCache.getInstance());
        assertEquals("Not same values", cache.values(), PlayerCache.getInstance().values());
        assertEquals(cache.get(0), PlayerCache.getInstance().get(0));
        cache.close();
    }
}