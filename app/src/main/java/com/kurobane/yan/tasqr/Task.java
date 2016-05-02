package com.kurobane.yan.tasqr;

public class Task {
    private String Name;
    private Integer totalRepetition;
    private Integer length;
    private boolean isPerforming;

    public Task () { }

    public Task(String name) {
        setName(name);
        isPerforming = false;
    }

    public void setName (String name) {
        Name = name;
    }

    public String getName () {
        return Name;
    }

    public void setTotalRepetition (Integer newVar) {
        totalRepetition = newVar;
    }

    public Integer getTotalRepetition () {
        return totalRepetition;
    }

    public void startPerforming() {
        isPerforming = true;
    }

    public void stopPerforming() {
        isPerforming = false;
    }

    public boolean isPerforming() {
        return isPerforming;
    }

    public void setLength (Integer newVar) {
        length = newVar;
    }

    public Integer getLength () {
        return length;
    }

    @Override
    public String toString() {
        return getName();
    }
}

