package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView taskTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        taskTitle = (TextView)findViewById(R.id.detail_text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Snackbar.make(view, "TODO replace with EditTask intent", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    Intent intent = new Intent(getApplicationContext(), EditActivity.class)
                            .putExtra(Intent.EXTRA_TITLE, taskTitle.getText().toString());
                    startActivity(intent);
                }
            });
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


}
