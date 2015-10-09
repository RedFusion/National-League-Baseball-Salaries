package ru.menkin.utils;

import ru.menkin.models.Player;
import java.util.Comparator;

/**
 * class for sorting players on name
 * @author Menkin
 */
public class SortedByName implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}

/**
 * class for sorting players on name upside-down
 * @author Menkin
 */
class SortedByNameReverse implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        return o2.getName().compareToIgnoreCase(o1.getName());
    }
}