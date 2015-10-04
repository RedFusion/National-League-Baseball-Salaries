package ru.menkin.utils;

import ru.menkin.models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Menkin
 */
public class SortCollection{
    private ArrayList<Player> list = new ArrayList<Player>();
    private String key;
    private String typeSort;

    public SortCollection(ArrayList<Player> list, String key, String typeSort) {
        this.list = list;
        this.key = key;
        this.typeSort = typeSort;
    }

    /**
     * sort Collection<Player>
     */

    //TODO refactor!!
    public void sortCollection()
    {
        if (key.equals("team")) {
            if (typeSort.equals("abc")) {
                Collections.sort(list, new SortedByTeam());
            } else{
                Collections.sort(list, new SortedByTeamReverse());
            }
        }

        Collections.sort(list, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
//                if (key.equals("team")) {
//                    if (typeSort.equals("abc")) {
//
//                        return o1.getTeam().compareTo(o2.getTeam());
//                    } else {
//                        return o2.getTeam().compareTo(o1.getTeam());
//                    }
//                }
                if (key.equals("name")) {
                    if (typeSort.equals("abc")) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    } else {
                        return o2.getName().compareToIgnoreCase(o1.getName());
                    }
                }
                if (key.equals("salary")) {
                    double s1 = Double.parseDouble(o1.getSalary());
                    double s2 = Double.parseDouble(o2.getSalary());
                    if (typeSort.equals("abc")) {
                        return Double.compare(s1, s2);
                    } else {
                        return Double.compare(s2, s1);
                    }
                }
                if (key.equals("position")) {
                    if (typeSort.equals("abc")) {
                        return o1.getPosition().compareTo(o2.getPosition());
                    } else {
                        return o2.getPosition().compareTo(o1.getPosition());
                    }
                }
                return 0;
            }
        });
    }
}
