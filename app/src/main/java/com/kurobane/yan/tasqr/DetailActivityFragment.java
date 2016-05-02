package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    TaskArray taskArray;
    Task currentTask;
    Button startTask;
    Button stopTask;
    public DetailActivityFragment() {
        taskArray = TaskArray.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        startTask = (Button) rootView.findViewById(R.id.start_task);
        stopTask = (Button) rootView.findViewById(R.id.stop_task);
        // The detail Activity called via intent.  Inspect the intent for forecast data.
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TITLE)) {
            String taskTitle = intent.getStringExtra(Intent.EXTRA_TITLE);
            currentTask = taskArray.getTaskById(taskArray.getTaskId(taskTitle));
            ((TextView) rootView.findViewById(R.id.detail_text)).setText(currentTask.getName());
        }


        startTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentTask.isPerforming()) {
                    TextView taskStatusTextView = (TextView)rootView.findViewById(R.id.task_status);
                    taskStatusTextView.setText(R.string.task_status_indicator_performing);
                    currentTask.startPerforming();
                }
            }
        });

        stopTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTask.isPerforming()) {
                    TextView taskStatusTextView = (TextView)rootView.findViewById(R.id.task_status);
                    taskStatusTextView.setText(R.string.task_status_indicator_not_performing);
                    currentTask.stopPerforming();
                }
            }
        });
        return rootView;
    }
}
