package com.kurobane.yan.tasqr;

public class Task {
    private String Name;
    private Integer totalRepetitions;
    private boolean isPerforming;

    public Task () {
        isPerforming = false;
        totalRepetitions = 0;
    }

    public Task(String name) {
        setName(name);
        isPerforming = false;
        totalRepetitions = 0;
    }

    public void setName (String name) {
        Name = name;
    }

    public String getName () {
        return Name;
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
        isPerforming = true;
    }

    public void stopPerforming() {
        isPerforming = false;
        addRepetition();
    }

    public boolean isPerforming() {
        return isPerforming;
    }

    @Override
    public String toString() {
        return getName();
    }
}

