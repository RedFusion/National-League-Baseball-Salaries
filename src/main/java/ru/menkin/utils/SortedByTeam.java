package ru.menkin.utils;

import ru.menkin.models.Player;
import java.util.Comparator;

/**
 * class for sorting players on team
 * @author Menkin
 */
public class SortedByTeam implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        return o1.getTeam().compareTo(o2.getTeam());
    }
}

/**
 * class for sorting players on team upside-down
 * @author Menkin
 */
class SortedByTeamReverse implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        return o2.getTeam().compareTo(o1.getTeam());
    }
}