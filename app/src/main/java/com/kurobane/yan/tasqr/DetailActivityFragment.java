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
    Task currentTask;
    Button startTask;
    Button stopTask;
    TextView taskName;
    TextView repetitionsTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MySQLiteHelper db = MySQLiteHelper.getInstance(getActivity());

        final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        startTask = (Button) rootView.findViewById(R.id.start_task);
        stopTask = (Button) rootView.findViewById(R.id.stop_task);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TITLE)) {
            int id = intent.getIntExtra(Intent.EXTRA_UID, 0);
            currentTask = db.getTask(id);
//            String taskTitle = intent.getStringExtra(Intent.EXTRA_TITLE);
//            currentTask = taskArray.getTaskById(taskArray.getTaskId(taskTitle));

//            taskName.setText(currentTask.getName());
            taskName = (TextView) rootView.findViewById(R.id.detail_text);
            taskName.setText(currentTask.getName());
        }

        repetitionsTextView = (TextView)rootView.findViewById(R.id.repetitions_texview);
        repetitionsTextView.setText(currentTask.getTotalRepetitionsToString());

        TextView taskStatusTextView = (TextView)rootView.findViewById(R.id.task_status);
        if (currentTask.isPerforming() == 1) {
            taskStatusTextView.setText(R.string.task_status_indicator_performing);
        } else {
            taskStatusTextView.setText(R.string.task_status_indicator_not_performing);
        }

        startTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTask.isPerforming() == 0) {
                    TextView taskStatusTextView = (TextView)rootView.findViewById(R.id.task_status);
                    taskStatusTextView.setText(R.string.task_status_indicator_performing);
                    currentTask.startPerforming();
                    db.updateTask(currentTask);
                    repetitionsTextView.setText(currentTask.getTotalRepetitionsToString());
                }
            }
        });

        stopTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTask.isPerforming() == 1) {
                    TextView taskStatusTextView = (TextView)rootView.findViewById(R.id.task_status);
                    taskStatusTextView.setText(R.string.task_status_indicator_not_performing);
                    currentTask.stopPerforming();
                    db.updateTask(currentTask);
                    repetitionsTextView.setText(currentTask.getTotalRepetitionsToString());
                }
            }
        });
        return rootView;
    }
}
