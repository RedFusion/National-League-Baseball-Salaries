package ru.menkin.store;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class PlayerStorage {
    public final ISpringStorage playerStorage;

    @Autowired
    public PlayerStorage(final ISpringStorage playerStorage) {
        this.playerStorage = playerStorage;
    }
}
