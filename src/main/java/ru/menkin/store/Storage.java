package ru.menkin.store;

import ru.menkin.models.*;

import java.util.*;

/**
 * @author Menkin
 * @since 12.10.2015
 */
public interface Storage {
    public Collection<Player> values();

    public int add(final Player player);

    public void close();

    public Player get(final int id);
}
