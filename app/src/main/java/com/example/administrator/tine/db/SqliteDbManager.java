package com.example.administrator.tine.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.tine.bean.Databean;
import com.example.administrator.tine.bean.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqliteDbManager {
    private static SqliteDbManager mInstance=null;
    private SQLiteDatabase mDb=null;
    private SQLiteOpenHelper mDbHelper=null;

    public static SqliteDbManager getInstance(){
        if(mInstance==null){
            mInstance=new SqliteDbManager();
        }
        return mInstance;
    }

    public void setSqliteDbOpen(Context context){
        mDbHelper = new DatabaseHelper(context.getApplicationContext());
        mDb = mDbHelper.getWritableDatabase();
    }

    public void insertTb(String tbName, List<Task> taskList){
        openDb();
        taskList=new ArrayList<>();
        for(int i=0;i<taskList.size();i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("task",taskList.get(i).task);
            contentValues.put("date", taskList.get(i).date);
            contentValues.put("sex",taskList.get(i).time);
            mDb.insert(tbName,null,contentValues);
        }
        closeDb();
    }

    public void deleteTb(String tbName,String task)
    {
        openDb();
        mDb.delete(tbName,"task=?",new String[]{task});
        closeDb();
    }

//    public void updateTb(String tbName)
//    {
//        openDb();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("task","隔壁老王");
//        contentValues.put("sex","男");
//        mDb.update(tbName,contentValues,"name=?",new String[]{"王五"});
//        closeDb();
//    }


    /**
     * @param tbName
     * @param date 按日期查询数据
     * @return
     */
    public List<Task> queryTbDate(String tbName, Date date)
    {
        List<Task> list=new ArrayList<>();
        openDb();
        //方法一：
        Cursor cursor = mDb.query(tbName, new String[]{"task","date","time"}, "date=?",new String[]{String.valueOf(date)}, null, null, null);
        //将光标移动到下一行，从而判断该结果集是否还有下一条数据；如果有则返回true，没有则返回false
        if (null != cursor)
        {
            while (cursor.moveToNext())
            {
                Task task=new Task();
                String task1 = cursor.getString(cursor.getColumnIndex("task"));
                String date1 = cursor.getString(cursor.getColumnIndex("date"));
                String time1 = cursor.getString(cursor.getColumnIndex("time"));
                task.task=task1;
                task.date= date1;
                task.time=time1;
                list.add(task);
                Log.i("????","name = "+task1+"; age = "+date1+"; sex = "+time1);

            }
            cursor.close();
        }
        closeDb();
        return list;
    }

    public List<Task> queryTbAll(String tbName)
    {
        List<Task> list=new ArrayList<>();
        openDb();
        //方法一：
        Cursor cursor = mDb.query(tbName, new String[]{"task","date","time"}, null,null, null, null, null);
        //将光标移动到下一行，从而判断该结果集是否还有下一条数据；如果有则返回true，没有则返回false
        if (null != cursor)
        {
            while (cursor.moveToNext())
            {
                Task task=new Task();
                String task1 = cursor.getString(cursor.getColumnIndex("task"));
                String date1 = cursor.getString(cursor.getColumnIndex("date"));
                String time1 = cursor.getString(cursor.getColumnIndex("time"));
                task.task=task1;
                task.date= date1;
                task.time=time1;
                list.add(task);
                Log.i("????","name = "+task1+"; age = "+date1+"; sex = "+time1);

            }
            cursor.close();
        }
        closeDb();
        return list;
    }

    /**
     *  创建或打开一个可以读的数据库
     */
    private void openDb() {
        if (this.mDbHelper != null) {
            try {
                mDb = mDbHelper.getWritableDatabase();
            } catch (Exception e) {
                mDb = mDbHelper.getReadableDatabase();
                e.printStackTrace();
            }
        }
    }

    /**
     *  关闭数据库
     */
    private void closeDb() {
        try {
            if (mDb != null) {
                mDb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
