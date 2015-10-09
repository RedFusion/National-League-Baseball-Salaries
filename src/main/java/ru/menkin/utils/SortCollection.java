package ru.menkin.utils;

import ru.menkin.models.Player;

import java.util.*;

/**
 * @author Menkin
 */
public class SortCollection {
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
    public void sortCollection() {
        Map<String, Comparator<Player>> map = new HashMap<String, Comparator<Player>>();
        map.put("teamabs", new SortedByTeam());
        map.put("teamdesc", new SortedByTeamReverse());
        map.put("nameabs", new SortedByName());
        map.put("namedesc", new SortedByNameReverse());
        map.put("salaryabs", new SortedBySalary());
        map.put("salarydesc", new SortedBySalaryReverse());
        map.put("positionabs", new SortedByPosition());
        map.put("positiondesc", new SortedByPositionReverse());

        Collections.sort(list, map.get(key + typeSort));
    }
}