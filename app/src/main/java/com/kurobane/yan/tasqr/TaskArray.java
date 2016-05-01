package com.kurobane.yan.tasqr;

import java.util.ArrayList;
import java.util.List;

public class TaskArray {
    private static List<Task> array;

    public TaskArray() {
        array = new ArrayList<>();
    }

    public List<Task> getArray() {
        return array;
    }

    public List<String> getToStringArray() {
        List<String> stringArray = new ArrayList<>();
        for (int i = 0; i < array.size(); ++i) {
            stringArray.add(array.get(i).toString());
        }
        return stringArray;
    }

    public void addTask(Task task) {
        array.add(task);
    }

    public Task getTaskById(int id) {
        return array.get(id);
    }

    public int getTaskId(String taskName) {
        for (int i = 0; i < array.size(); ++i) {
            if (array.get(i).getName().equals(taskName)) {
                return array.indexOf(array.get(i));
            }
        }
        return 0;
    }

    public int getTaskId(Task task) {
        return array.indexOf(task);
    }
}
