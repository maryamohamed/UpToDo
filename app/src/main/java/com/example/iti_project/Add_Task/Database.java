package com.example.iti_project.Add_Task;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    // Variables
    public static final String DB_NAME = "Tasks_db";
    public static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "task";
    public static final String ID = "id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_DESCRIPTION = "task_description";
    public static final String TASK_DATE = "task_date";
    public static final String TASK_TIME = "task_time";




    public Database (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TASK_NAME+" TEXT, "+TASK_DESCRIPTION+" TEXT, "+TASK_DATE+" TEXT, "+TASK_TIME+" TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void write_into_database() {
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void insert_task (TASK task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, task.getName());
        contentValues.put(TASK_DESCRIPTION, task.getDescription());
        contentValues.put(TASK_DATE, task.getDate());
        contentValues.put(TASK_TIME, task.getTime());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public void update_task (int id, String name, String description, String date, String time) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, name);
        contentValues.put(TASK_DESCRIPTION, description);
        contentValues.put(TASK_DATE, date);
        contentValues.put(TASK_TIME, time);
        db.update(TABLE_NAME, contentValues, ID +"= ?", new String[] {String.valueOf(id)});
    }

    @SuppressLint("Range")
    public ArrayList<TASK> get_all_tasks(){
        ArrayList<TASK> tasks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        db.beginTransaction();
        try{
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
            if(cursor != null){
                if(cursor.moveToFirst()){
                    do{
                        TASK task = new TASK();
                        task.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        task.setName(cursor.getString(cursor.getColumnIndex(TASK_NAME)));
                        task.setDescription(cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION)));
                        task.setDate(cursor.getString(cursor.getColumnIndex(TASK_DATE)));
                        task.setTime(cursor.getString(cursor.getColumnIndex(TASK_TIME)));
                        tasks.add(task);
                    }
                    while(cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cursor != null;
            cursor.close();
        }
        return tasks;
    }

    @SuppressLint("Range")
    public ArrayList<TASK> get_task(String name){
        ArrayList<TASK> tasks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+TASK_NAME+" LIKE ?",new String[]{name+"%"});
        db.beginTransaction();
        try{
            if(cursor != null && cursor.moveToFirst()){
                    do{
                        TASK task = new TASK();
                        task.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        task.setName(cursor.getString(cursor.getColumnIndex(TASK_NAME)));
                        task.setDescription(cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION)));
                        task.setDate(cursor.getString(cursor.getColumnIndex(TASK_DATE)));
                        task.setTime(cursor.getString(cursor.getColumnIndex(TASK_TIME)));
                        tasks.add(task);
                    }
                    while(cursor.moveToNext());
                }
        }
        finally {
            db.endTransaction();
            assert cursor != null;
            cursor.close();
        }
        return tasks;
    }

    public long get_tasks_count () {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,TABLE_NAME);
    }

    public void delete_task (int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID +"= ?", new String[] {String.valueOf(id)});
    }
}
