package ru.menkin.models;

import java.util.*;

/**
 * @author Menkin
 * @since 28.10.2015
 */
public class Team {
    private int id;
    private String team;
    private List<String> players;

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}