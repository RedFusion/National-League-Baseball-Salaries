package ru.menkin.store;

import ru.menkin.models.Player;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton for work with class Player
 * @author Menkin
 */
public class PlayerCache
{
    private static final PlayerCache INSTANCE = new PlayerCache();

    private final ConcurrentHashMap<Integer, Player> players = new ConcurrentHashMap<Integer, Player>();

    public static PlayerCache getInstance()
    {
        return INSTANCE;
    }

    public Collection<Player> values()
    {
        return this.players.values();
    }

    public void add(final Player player)
    {
        this.players.put(player.getId(), player);
    }
}