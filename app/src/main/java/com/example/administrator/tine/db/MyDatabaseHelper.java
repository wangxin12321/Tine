package com.example.administrator.tine.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //创建表
    public static final String CREATE_BOOK="create table TaskBook("
    +"id integer primary key autoincrement,"
    +"time text,"
    +"date text,"
    +"task text)";

    private Context context;
    private static MyDatabaseHelper myDatabaseHelper=null;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(context,"Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

//    //插入
//    public void insert(String table, ContentValues contentValues){
//        synchronized (myDatabaseHelper){
//            SQLiteDatabase database=myDatabaseHelper.getWritableDatabase();
//            database.insert(table,null,contentValues);
//        }
//    }
//
//    //删除
//    public void delete(String table,String whereClause,String [] whereArgs){
//        synchronized (myDatabaseHelper){
//            SQLiteDatabase database=myDatabaseHelper.getWritableDatabase();
//            database.delete(table,whereClause,whereArgs);
//        }
//    }
//
//    //更新
//    public void update(String table,ContentValues contentValues,String whereClause,String[] whereArgs){
//        synchronized (myDatabaseHelper){
//            SQLiteDatabase database=myDatabaseHelper.getWritableDatabase();
//            database.update(table,contentValues,whereClause,whereArgs);
//        }
//    }



}
