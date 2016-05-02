package com.kurobane.yan.tasqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivityFragment extends Fragment {
    ArrayAdapter<String> mTaskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MySQLiteHelper db = MySQLiteHelper.getInstance(getActivity());

        final List<Task> tasks = db.getAllTasks();
        List<String> tasksStringArray = db.getToStringArray();

        mTaskAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_task,
                R.id.list_item_task_textview,
                tasksStringArray
        );

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_task);
        listView.setAdapter(mTaskAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String title = mTaskAdapter.getItem(position);
                int id = 0;
                for (int i = 0; i < tasks.size(); ++i) {
                    if (tasks.get(i).getName().equals(title)) {
                        id = tasks.get(i).getId();
                    }
                }

                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TITLE, title)
                        .putExtra(Intent.EXTRA_UID, id);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
