package ru.menkin.models;

/**
 * POJO Model
 */
public class Player {
    private int id;
    private String team;
    private String name;
    private String salary;
    private String position;

    public Player() {}

    public Player(final int id, final String team, final String name, final String salary, final String position) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.salary = salary;
        this.position = position;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}