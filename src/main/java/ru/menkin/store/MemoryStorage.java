package ru.menkin.store;

import ru.menkin.models.*;

import java.util.*;
import java.util.concurrent.*;

public class MemoryStorage implements Storage {
    private final ConcurrentHashMap<Integer, Player> players = new ConcurrentHashMap<Integer, Player>();

    @Override
    public Collection<Player> values() {
        return this.players.values();
    }

    @Override
    public int add(Player player) {
        this.players.put(player.getId(), player);
        return player.getId();
    }

    @Override
    public Player get(final int id) {
        return this.players.get(id);
    }

    @Override
    public void close() {}
}
