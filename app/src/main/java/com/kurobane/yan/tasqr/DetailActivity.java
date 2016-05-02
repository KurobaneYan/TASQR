package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView taskTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MySQLiteHelper db = MySQLiteHelper.getInstance(this);

        taskTitle = (TextView)findViewById(R.id.detail_text);

        final List<Task> tasks = db.getAllTasks();
        final String title = taskTitle.getText().toString();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = 0;
                    for (int i = 0; i < tasks.size(); ++i) {
                        if (tasks.get(i).getName().equals(title)) {
                            id = tasks.get(i).getId();
                        }
                    }
                    Intent intent = new Intent(getApplicationContext(), EditActivity.class)
                            .putExtra(Intent.EXTRA_TITLE, taskTitle.getText().toString())
                            .putExtra(Intent.EXTRA_UID, id);
                    startActivity(intent);
                }
            });
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


}
