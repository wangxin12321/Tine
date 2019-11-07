package com.example.administrator.tine.pager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.tine.R;
import com.example.administrator.tine.activity.AddTaskActivity;
import com.example.administrator.tine.base.BasePager;
import com.example.administrator.tine.bean.DayFinish;
import com.example.administrator.tine.view.CustomCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomePager extends BasePager {

    public static final String DATE_TIME="date_time";

    private CustomCalendar cal;


    public HomePager(Activity context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        View view= View.inflate(context,R.layout.home_pager,null);
        cal = view.findViewById(R.id.cal);


        //4.绑定数据
        //TODO 模拟请求当月数据
        final List<DayFinish> list = new ArrayList<>();
        list.add(new DayFinish(1,2,2));
        list.add(new DayFinish(2,1,2));
        list.add(new DayFinish(3,0,2));
        list.add(new DayFinish(4,2,2));
        list.add(new DayFinish(5,2,2));
        list.add(new DayFinish(6,2,2));
        list.add(new DayFinish(7,2,2));
        list.add(new DayFinish(8,0,2));
        list.add(new DayFinish(9,1,2));
        list.add(new DayFinish(10,2,2));
        list.add(new DayFinish(11,5,2));
        list.add(new DayFinish(12,2,2));
        list.add(new DayFinish(13,2,2));
        list.add(new DayFinish(14,3,2));
        list.add(new DayFinish(15,2,2));
        list.add(new DayFinish(16,1,2));
        list.add(new DayFinish(17,0,2));
        list.add(new DayFinish(18,2,2));
        list.add(new DayFinish(19,2,2));
        list.add(new DayFinish(20,0,2));
        list.add(new DayFinish(21,2,2));
        list.add(new DayFinish(22,1,2));
        list.add(new DayFinish(23,2,0));
        list.add(new DayFinish(24,0,2));
        list.add(new DayFinish(25,2,2));
        list.add(new DayFinish(26,2,2));
        list.add(new DayFinish(27,2,2));
        list.add(new DayFinish(28,2,2));
        list.add(new DayFinish(29,2,2));
        list.add(new DayFinish(30,2,2));
        list.add(new DayFinish(31,2,2));

        fl_content_pager.removeAllViews();
        //获取当前时间为默认时间
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
        String time=df.format(date);
        cal.setRenwu(time, list);
        cal.setOnClickListener(new CustomCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {
                Toast.makeText(context, "点击减箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(-1);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(1000);
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setRenwu(list);
                                }
                            });
                        }catch (Exception e){
                        }
                    }
                }.start();
            }

            @Override
            public void onRightRowClick() {
                Toast.makeText(context, "点击加箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(1);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(1000);
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setRenwu(list);
                                }
                            });
                        }catch (Exception e){
                        }
                    }
                }.start();
            }

            @Override
            public void onTitleClick(String monthStr, Date month) {
                Toast.makeText(context, "点击了标题："+monthStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {
                Toast.makeText(context, "点击了星期："+weekStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayClick(int day, String dayStr, DayFinish finish) {
                Toast.makeText(context, "点击了日期："+dayStr, Toast.LENGTH_SHORT).show();
                Log.w("", "点击了日期:"+dayStr);
                Intent intent=new Intent(context, AddTaskActivity.class);
                intent.putExtra(DATE_TIME,dayStr);
                context.startActivity(intent);
            }
        });

        fl_content_pager.addView(view);
    }

}
