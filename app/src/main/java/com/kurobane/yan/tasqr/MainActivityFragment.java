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

public class MainActivityFragment extends Fragment {
    ArrayAdapter<String> mTaskAdapter;
    TaskArray allTasks;

    public MainActivityFragment() {
        allTasks = TaskArray.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTaskAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_task,
                R.id.list_item_task_textview,
                allTasks.getToStringArray()
        );

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_task);
        listView.setAdapter(mTaskAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String title = mTaskAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TITLE, title);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
