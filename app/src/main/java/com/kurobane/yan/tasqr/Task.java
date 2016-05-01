package com.kurobane.yan.tasqr;

public class Task {
    private String Name;
    private Integer totalRepetition;
    private Integer length;

    public Task () { }

    public Task(String name) {
        setName(name);
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


    public void setLength (Integer newVar) {
        length = newVar;
    }

    public Integer getLength () {
        return length;
    }

    @Override
    public String toString() {
        String output = "Name:\n" + getName();
        return output;
    }
}

