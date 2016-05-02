package com.kurobane.yan.tasqr;

public class Task {
    private int id;
    private String Name;
    private Integer totalRepetitions;
    private int isPerforming;

    public Task () {
        isPerforming = 0;
        totalRepetitions = 0;
    }

    public Task(String name) {
        setName(name);
        isPerforming = 0;
        totalRepetitions = 0;
    }

    public void setName (String name) {
        Name = name;
    }

    public String getName () {
        return Name;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTotalRepetitions(Integer newRepetitions) {
        totalRepetitions = newRepetitions;
    }

    public Integer getTotalRepetitions() {
        return totalRepetitions;
    }

    public String getTotalRepetitionsToString() {
        return totalRepetitions.toString();
    }

    public void addRepetition() {
        totalRepetitions += 1;
    }

    public void startPerforming() {
        isPerforming = 1;
    }

    public void stopPerforming() {
        isPerforming = 0;
        addRepetition();
    }

    public int isPerforming() {
        return isPerforming;
    }

    public void setIsPerforming(int value) {
        isPerforming = value;
    }

    @Override
    public String toString() {
        return getName();
    }
}

