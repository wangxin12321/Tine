package com.example.administrator.tine.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.tine.bean.Databean;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBOperator {

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;


    public SQLiteDBOperator(Context context) {
        //数据库名
        myDatabaseHelper=new MyDatabaseHelper(context,"dp_sqlite",null,1);
        //初始化数据库操作对象
        db=myDatabaseHelper.getWritableDatabase();
    }



    //增加一条学生数据
    public void addData(Databean bean_add) {
        //这里因为id设置为自增，那么就不设置，也是因此插入数据时必须要指明字段，
        // 不能使用默认的字段（含id，但是没有id数据），会报错
        db.execSQL("insert into Databean(date,time,content) values(?,?,?)"
                , new Object[] {bean_add.getDate(), bean_add.getTime(), bean_add.getContent()});
    }

//    //更新某一学生的数据
//    public void updateData(Databean bean_update) {
//        db.execSQL("update Databean set date=?,time=?",
//                new Object[] {bean_update.getDate(), bean_update.getTime() });
//    }

    //删除某一学生的数据
    public void deleteData(Databean bean_delete) {
        db.delete("date=?","time=? and content=?", (String[]) new Object[]{bean_delete.getDate(), bean_delete.getTime(), bean_delete.getContent()});
    }

    //查询 获取所有数据
    public List<Databean> findAllDataBean(){
        List<Databean> list=new ArrayList<>();
        Cursor cursor=db.rawQuery("select*from Databean",null);
        while(cursor.moveToNext()){
            Databean databean=new Databean();
            databean.setDate(cursor.getString(1));
            databean.setTime(cursor.getString(2));
            databean.setContent(cursor.getString(3));
            list.add(databean);
        }
        cursor.close();
        return list;
    }

    //查询 获取某一天的数据
    public List<Databean> findDateDataBean(String time){
        List<Databean> list=new ArrayList<>();
        Cursor cursor=db.rawQuery("select*from Databean",null);
        while(cursor.moveToNext()){
            if(time==cursor.getString(1)){
                Databean databean=new Databean();
                databean.setDate(cursor.getString(1));
                databean.setTime(cursor.getString(2));
                databean.setContent(cursor.getString(3));
                list.add(databean);
            }

        }
        cursor.close();
        return list;
    }

}
