package com.example.administrator.tine.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.tine.bean.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private DBHelp dbHelp;
    private SQLiteDatabase db;

    public TaskDao(Context context){
        dbHelp = new DBHelp(context);
    }

    public void save(List<Task> taskList){
        db = dbHelp.getWritableDatabase();
        for(int i=0;i<taskList.size();i++){
            String sql = "insert into t_task(task,date,time) values(?,?,?)";
            db.execSQL(sql, new String[]{taskList.get(i).getTask(),taskList.get(i).getDate(),taskList.get(i).getTime()});
        }

        db.close();
    }

//    public void update(Task task){
//        db = dbHelp.getWritableDatabase();
//        String sql = "update t_task set task = ?,date = ? ,time=? where _id = ?";
//        db.execSQL(sql,new Object[]{user.getUsername(),user.getPassword(),user.getId()});
//        db.close();
//    }

    public void del(Task task){
        db = dbHelp.getWritableDatabase();
        String sql = "delete from t_task where task= ? and date = ? and time = ?";
        db.execSQL(sql,new Object[]{task.getTask(),task.getDate(),task.getTime()});
        db.close();
    }

    public List<Task> findAll(){
        List<Task> list = new ArrayList<Task>();
        db = dbHelp.getReadableDatabase();
        String sql = "select task,date,time from t_task";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            Task task = new Task();
            task.setTask(cursor.getString(cursor.getColumnIndex("task")));
            task.setDate(cursor.getString(cursor.getColumnIndex("date")));
            task.setTime(cursor.getString(cursor.getColumnIndex("time")));
            list.add(task);
        }
        db.close();
        return list;
    }

    public List<Task> findByDate(String date){
        List<Task> list=new ArrayList<>();
        db = dbHelp.getReadableDatabase();
        String sql = "select task,date,time from t_task where date = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{date});

        while(cursor.moveToNext()){
            Task task=new Task();
            task.setTask(cursor.getString(cursor.getColumnIndex("task")));
            task.setDate(cursor.getString(cursor.getColumnIndex("date")));
            task.setTime(cursor.getString(cursor.getColumnIndex("time")));
            list.add(task);
        }
        db.close();
        return list;
    }
}
