package ru.menkin.store;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * @author Menkin
 * @since 10.11.2015
 */
@Service
public class Storages {
    public final PlayerStorage playerStorage;

    @Autowired
    public Storages(final PlayerStorage playerStorage) {
        this.playerStorage = playerStorage;
    }
}
