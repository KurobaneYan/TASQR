package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddTaskActivityFragment extends Fragment {
    Button addTaskButton;
    EditText editTaskName;
    TaskArray allTasks;
    public AddTaskActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        allTasks = TaskArray.getInstance();
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);
        editTaskName = (EditText) rootView.findViewById(R.id.edit_task_name);
        addTaskButton = (Button) rootView.findViewById(R.id.button_add_task);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(editTaskName.getText().toString());
                allTasks.addTask(task);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
