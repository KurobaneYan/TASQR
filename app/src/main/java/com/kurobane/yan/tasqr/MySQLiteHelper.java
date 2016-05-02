package com.kurobane.yan.tasqr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static MySQLiteHelper instance = null;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TosterTasksDB";
    private static final String TABLE_TASKS = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String Key_REPETITIONS = "repetitions";
    private static final String KEY_PERFORMING = "performing";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, Key_REPETITIONS, KEY_PERFORMING};

    public static MySQLiteHelper getInstance(Context activityContext) {

        // Get the application context from the activityContext to prevent leak
        if (instance == null) {
            instance = new MySQLiteHelper (activityContext.getApplicationContext());
        }
        return instance;
    }

    private MySQLiteHelper(Context aplicationContext) {
        super(aplicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "repetitions INTEGER, " +
                "performing INTEGER )";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        this.onCreate(db);
    }

    public void addTask(Task task) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(Key_REPETITIONS, task.getTotalRepetitions());
        values.put(KEY_PERFORMING, task.isPerforming());
        // 3. insert
        db.insert(TABLE_TASKS,
                null,
                values);
        // 4 . close
        db.close();
    }

    public Task getTask(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_TASKS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        Task task = new Task();
        // 3. if we got results get the first one
        if (cursor != null) {
            cursor.moveToFirst();

        // 4. fill task object
            task.setId(Integer.parseInt(cursor.getString(0)));
            task.setName(cursor.getString(1));
            task.setTotalRepetitions(Integer.parseInt(cursor.getString(2)));
            task.setIsPerforming(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        }

        // 5. return task
        return task;
    }

    // Get All Books
    public List<Task> getAllTasks() {
//        List<Task> tasks = new LinkedList<Task>();
        List<Task> tasks = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_TASKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build task and add it to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setTotalRepetitions(Integer.parseInt(cursor.getString(2)));
                task.setIsPerforming(Integer.parseInt(cursor.getString(3)));

                // Add task to tasks
                tasks.add(task);
            } while (cursor.moveToNext());
        }

        Log.d("getAllTasks()", tasks.toString());
        cursor.close();
        // return tasks
        return tasks;
    }

    public List<String> getToStringArray() {
        List<String> stringArray = new ArrayList<>();
        List<Task> taskArray = getAllTasks();
        for (int i = 0; i < taskArray.size(); ++i) {
            stringArray.add(taskArray.get(i).toString());
        }
        return stringArray;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", task.getName());
        values.put("repetitions", task.getTotalRepetitions());
        values.put("performing", task.isPerforming());

        int i = db.update(TABLE_TASKS,
                values,
                KEY_ID+" = ?",
                new String[] {String.valueOf(task.getId())});

        db.close();

        return 1;
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TASKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(task.getId()) });

        db.close();
    }
}
