package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    Task currentTask;
    TextView taskName;
    EditText newTaskName;
    Button changeTaskName;
    Button deleteTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MySQLiteHelper db = MySQLiteHelper.getInstance(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TITLE)) {
            String taskTitle = intent.getStringExtra(Intent.EXTRA_TITLE);
            int id = intent.getIntExtra(Intent.EXTRA_UID, 0);
            currentTask = db.getTask(id);
//            currentTask = taskArray.getTaskById(taskArray.getTaskId(taskTitle));
            taskName = (TextView) findViewById(R.id.current_task_name);
            taskName.setText(currentTask.getName());
        }

        newTaskName = (EditText) findViewById(R.id.new_task_name);

        changeTaskName = (Button) findViewById(R.id.button_change_task_name);
        changeTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTask.setName(newTaskName.getText().toString());
                db.updateTask(currentTask);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        deleteTask = (Button) findViewById(R.id.button_delete_task);
        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteTask(currentTask);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
