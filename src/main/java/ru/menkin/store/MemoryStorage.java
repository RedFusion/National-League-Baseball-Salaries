package ru.menkin.store;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.menkin.models.*;

import java.util.*;
import java.util.concurrent.*;

@Repository
public class MemoryStorage implements Storage<Player> {
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
    public void delete(int id) {
        players.remove(id);
    }

    @Override
    public void edit(final Player player) {
        this.players.replace(player.getId(), player);
    }

    @Override
    public void close() {}

}
