package com.kurobane.yan.tasqr;

public class Task {
    private String Name;
    private String Location;
    private boolean isPerforming;
    private boolean isHabit;
    private Integer totalRepetition;
    private Integer length;
    private Integer daysBeforeRepetition;

    public Task () { }

    public Task(String name) {
        setName(name);
    }

    private boolean getIsPerforming () {
        return isPerforming;
    }

    private void setName (String name) {
        Name = name;
    }

    private String getName () {
        return Name;
    }

    private void setLocation (String location) {
        Location = location;
    }

    private String getLocation () {
        return Location;
    }

    private void setIsHabit (boolean isHabit) {
        this.isHabit = isHabit;
    }

    private boolean getIsHabit () {
        return isHabit;
    }

    private void setTotalRepetition (Integer newVar) {
        totalRepetition = newVar;
    }

    private Integer getTotalRepetition () {
        return totalRepetition;
    }


    private void setLength (Integer newVar) {
        length = newVar;
    }

    private Integer getLength () {
        return length;
    }

    private void setDaysBeforeRepetition (Integer newVar) {
        daysBeforeRepetition = newVar;
    }

    private Integer getDaysBeforeRepetition () {
        return daysBeforeRepetition;
    }

    public void start()
    {
    }

    public void finish()
    {
    }

    private void SetNewBeginning()
    {
    }

    @Override
    public String toString() {
        return getName();
    }
}

