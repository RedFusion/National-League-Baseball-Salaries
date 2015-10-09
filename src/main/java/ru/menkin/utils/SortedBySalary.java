package ru.menkin.utils;

import ru.menkin.models.*;

import java.util.*;

/**
 * class for sorting players on salary
 * @author Menkin
 */
public class SortedBySalary implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        double s1 = Double.parseDouble(o1.getSalary());
        double s2 = Double.parseDouble(o2.getSalary());
        return Double.compare(s1, s2);
    }
}
/**
 * class for sorting players on salary upside-down
 * @author Menkin
 */
class SortedBySalaryReverse implements Comparator<Player> {
    public int compare(Player o1, Player o2) {
        double s1 = Double.parseDouble(o1.getSalary());
        double s2 = Double.parseDouble(o2.getSalary());
        return Double.compare(s2, s1);
    }
}
