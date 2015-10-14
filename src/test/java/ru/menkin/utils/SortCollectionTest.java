package ru.menkin.utils;

import org.junit.*;
import ru.menkin.models.*;

import java.util.*;

/**
 * @author Menkin
 * @since 06.10.2015
 */
public class SortCollectionTest extends Assert{
    @Test
    public void test(){
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(new Player(1, "A", "Bob", "30000.0", "Cather"));
        list.add(new Player(2, "B", "Arni", "31000.0", "Pitcher"));

        ArrayList<Player> sortedList = new ArrayList<Player>();
        sortedList.add(new Player(2, "B", "Arni", "31000.0", "Pitcher"));
        sortedList.add(new Player(1, "A", "Bob", "30000.0", "Cather"));

        SortCollection sort = new SortCollection(list, "name", "abs");
        sort.sortCollection();

        for (int i = 0; i < list.size(); i++) {
            assertEquals("Wrong sorting by name! ", sortedList.get(i).getName(), list.get(i).getName());
        }

        sort = new SortCollection(list, "team", "desc");
        sort.sortCollection();

        for (int i = 0; i < list.size(); i++) {
            assertEquals("Wrong sorting by team! ", sortedList.get(i).getTeam(), list.get(i).getTeam());
        }

        sort = new SortCollection(list, "salary", "abs");
        sort.sortCollection();
        assertEquals("Wrong sorting by salary abs! ", sortedList.get(1).getSalary(), list.get(0).getSalary());

        sort = new SortCollection(list, "salary", "desc");
        sort.sortCollection();

        for (int i = 0; i < list.size(); i++) {
            assertEquals("Wrong sorting by salary! ", sortedList.get(i).getSalary(), list.get(i).getSalary());
        }

        sort = new SortCollection(list, "position", "desc");
        sort.sortCollection();

        for (int i = 0; i < list.size(); i++) {
            assertEquals("Wrong sorting by position! ", sortedList.get(i).getPosition(), list.get(i).getPosition());
        }
    }
}