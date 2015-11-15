package ru.menkin.store;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Menkin
 * @since 12.10.2015
 */
public interface Storage <Player> {
    public Collection<Player> values();

    public int add(final Player player);

    public void close();

    public Player get(final int id);

    public void delete(final int id);

    public void edit(final Player player);
}
