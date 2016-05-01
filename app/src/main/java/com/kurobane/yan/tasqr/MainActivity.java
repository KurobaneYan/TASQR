package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TaskArray allTasks;
    ArrayAdapter<String> mTaskAdapter;

    public MainActivity() {
        allTasks = TaskArray.getInstance();
        allTasks.addTask(new Task("Test"));
        allTasks.addTask(new Task("Test2"));
        allTasks.addTask(new Task("Test3"));
        allTasks.addTask(new Task("Test4"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
                    startActivity(intent);
                }
            });
        }

//        Date date = new Date();
//        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
//        mTimeText.setText("Time: " + dateFormat.format(date));

        new CountDownTimer(30000, 1000) {
            TextView mTextField = (TextView) findViewById(R.id.time);
            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
