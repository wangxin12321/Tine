package com.example.administrator.tine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;//版本号
    private static final String DB_NAME="task.db";//数据库名
    public static final String TABLE_NAME="task";//表明

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * @param sqLiteDatabase
     * c创建表和初始化数据
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据库后，对数据库的操作
        String createTaStr="create table if not exists "+TABLE_NAME+"(_id integer primary key,task varcher,date carcher,time carchar)";
        sqLiteDatabase.execSQL(createTaStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新数据库版本的操作
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //每次打开数据库后首先被执行
    }
}
