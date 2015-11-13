package ru.menkin.store;

import ru.menkin.models.*;

import java.util.*;

/**
 * Singleton for work with class Player
 * @author Menkin
 */
public class PlayerCache implements Storage<Player>{
    private static final PlayerCache INSTANCE = new PlayerCache();

    private final MemoryStorage storage = new MemoryStorage();

    public static PlayerCache getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Player> values() {
        return storage.values();
    }

    @Override
    public int add(final Player player) {
        return this.storage.add(player);
    }

    @Override
    public Player get(final int id){
        return storage.get(id);
    }

    @Override
    public void delete(int id) {
        storage.delete(id);
    }

    @Override
    public void edit(final Player player) {
        storage.edit(player);
    }

    @Override
    public void close(){
        this.storage.close();
    }
}