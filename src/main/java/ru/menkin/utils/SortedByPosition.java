package ru.menkin.utils;

import ru.menkin.models.*;

import java.util.*;

/**
 * class for sorting players on position
 * @author Menkin
 */
public class SortedByPosition implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        return o1.getPosition().compareTo(o2.getPosition());
    }
}
/**
 * class for sorting players on position upside-down
 * @author Menkin
 */
class SortedByPositionReverse implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        return o2.getPosition().compareTo(o1.getPosition());
    }
}
