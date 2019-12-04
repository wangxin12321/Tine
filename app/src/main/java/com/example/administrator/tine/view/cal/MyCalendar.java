package com.example.administrator.tine.view.cal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tine.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class MyCalendar extends LinearLayout {
    private ImageView btnPre;
    private ImageView btnNext;
    private TextView textDate;
    public GridView grid;
    public TextView text_calendar_day;
    public ArrayList<Date> cells;

    //初始化
    private Calendar curDate=Calendar.getInstance();

    private String disPlayFormat;

    private MyCalendarListener myCalendarListener;



    public MyCalendar(Context context) {
        super(context);
    }

    public MyCalendar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context,attrs);
    }

    private void initControl(Context context,AttributeSet attrs) {
        //绑定控件
        bindControl(context);
        //绑定事件
        bindControlEvent();

        TypedArray typedArray=getContext().obtainStyledAttributes(attrs,R.styleable.MyCalTextView);
        try {
            String format = typedArray.getString(R.styleable.MyCalTextView_dateformat);
            disPlayFormat=format;
            if(disPlayFormat==null){
                disPlayFormat="MMM YYYY";
            }
        }
        finally {
            typedArray.recycle();
        }
        //渲染
        renderCalender();

    }

    private void bindControlEvent() {
        btnPre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH,-1);
                renderCalender();
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH,1);
                renderCalender();
            }
        });
    }



    private void bindControl(Context context) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.calendar_view,this);
        btnPre=(ImageView) findViewById(R.id.btnPre);
        btnNext=(ImageView) findViewById(R.id.btnNext);
        textDate=(TextView) findViewById(R.id.textDate);
        grid=(GridView)findViewById(R.id.calendar_grid);
    }

    private void renderCalender() {
        SimpleDateFormat sdf=new SimpleDateFormat(disPlayFormat);
        textDate.setText(sdf.format(curDate.getTime()));

        cells=new ArrayList<>();
        Calendar calendar=(Calendar) curDate.clone();
        //将时间设置为当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH,1);
        //表示上个剩余的天数
        int prevDays=calendar.get(Calendar.DAY_OF_WEEK)-1;
        //将日历天数，移到了需要绘制的第一天
        calendar.add(Calendar.DAY_OF_MONTH,-prevDays);
        //最多的行数
        final int maxCellCount=6*7;
        while(cells.size()<maxCellCount){
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
        CalendarAdapter calendarAdapter=new CalendarAdapter(getContext(),cells);
        grid.setAdapter(calendarAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(myCalendarListener==null){
                    Toast.makeText(getContext(), "123123", Toast.LENGTH_SHORT).show();
                }else {
                    myCalendarListener.OnItemClick(view,(Date)parent.getItemAtPosition(position));
                }
            }
        });

    }



    private class CalendarAdapter extends ArrayAdapter<Date>{
        private LayoutInflater layoutInflater;

        public CalendarAdapter(Context context,ArrayList <Date> days) {
            super(context, R.layout.text_item,days);
            layoutInflater=LayoutInflater.from(context);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Date date=getItem(position);
            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.text_item,parent,false);
                text_calendar_day=convertView.findViewById(R.id.text_calendar_day);
            }

            int day=date.getDate();
            ((TextView)convertView).setText(String.valueOf(day));

            Date now=new Date();

            boolean isSameMonth=false;
            if(now.getMonth()==date.getMonth()){
                isSameMonth=true;
            }


            if(isSameMonth){
                ((TextView) convertView).setTextColor(Color.parseColor("#000000"));
            }else {
                ((TextView)convertView).setTextColor(Color.parseColor("#55000000"));
            }

            if(now.getDate()==date.getDate()&&date.getMonth()==now.getMonth()
                    &&now.getDay()==date.getDay()){

                ((MyCalTextView)convertView).isToday=true;
                ((TextView)convertView).setTextColor(Color.BLUE);


            }
            return convertView;
        }
    }

    public interface MyCalendarListener{
        void OnItemClick(View view, Date day);
    }

    public void setOnItemCliclListener(MyCalendarListener myCalendarListener){
        this.myCalendarListener=myCalendarListener;
    }

}
