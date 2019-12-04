package com.example.administrator.tine.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.tine.R;
import com.example.administrator.tine.activity.AddTaskActivity;
import com.example.administrator.tine.base.BasePager;
import com.example.administrator.tine.view.cal.MyCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePager extends BasePager implements MyCalendar.MyCalendarListener{

    private MyCalendar myCalendar;
    private View lastView;

    public HomePager(Activity context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        fl_content_pager.removeAllViews();
        View view= View.inflate(context,R.layout.home_pager,null);
        myCalendar=view.findViewById(R.id.home_pager_cal);
        myCalendar.setOnItemCliclListener(this);
        fl_content_pager.addView(view);
    }

    @Override
    public void OnItemClick(View view, Date day) {
        view.setBackgroundColor(Color.RED);
        lastView = view;
        Intent intent=new Intent(context,AddTaskActivity.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(day);
        Toast.makeText(context, "123"+date, Toast.LENGTH_SHORT).show();
        intent.putExtra("date",date);
        context.startActivity(intent);
    }
}
