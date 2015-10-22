package ru.menkin.store;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.menkin.models.*;

import java.util.*;

@Repository
public class FakeStorage implements Storage {

    private final MemoryStorage memoryStorage;
    @Autowired
    public FakeStorage(final MemoryStorage memoryStorage){
        this.memoryStorage = memoryStorage;
    }

    public Collection<Player> values() {
        return null;
    }

    public int add(Player player) {
        return 0;
    }

    public void close() {

    }

    public Player get(int id) {
        return null;
    }

    public void delete(int id) {

    }

    public void edit(Player player) {

    }
}
