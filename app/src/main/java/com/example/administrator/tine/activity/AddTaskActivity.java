package com.example.administrator.tine.activity;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.administrator.tine.R;
import com.example.administrator.tine.adapter.AddTaskAdapter;
import com.example.administrator.tine.alarm.AlarmReceiver;
import com.example.administrator.tine.bean.Task;

import com.example.administrator.tine.db.TaskDao;
import com.example.administrator.tine.utils.LogUtil;
import com.example.administrator.tine.view.cal.MyCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class AddTaskActivity extends AppCompatActivity implements MyCalendar.MyCalendarListener {

    private RecyclerView mRecyclerView;
    private RelativeLayout rl_add;
    private List<Task> tasklist;
    private AddTaskAdapter adapter;
    private LinearLayout ll_acty_layout;
    private String DEFAULT_TIME;


    private List<Task> mTaskList;

    //数据库
    private TaskDao taskDao;


    //TASK描述常量
    private String SELECT_HOUR;
    private String SELECT_TIME;
    private String TASK_DESCRIBE;


    //pop控件
    private EditText et_pop_add_task;
    private Spinner sp_pop_task_hour;
    private Spinner sp_pop_task_min;
    private MyCalendar mc_pop_add_task;
    private Button confirm_pop_add_task;

    //list标记
    private List<Date> dateList=new ArrayList<>();
    private static int size;
    private boolean flag=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_activity);
        initView();
        getDateTime();
        initRecycler();
        rl_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击添加事件弹出事件窗口
                showPopueWindow();
            }
        });
    }

    private void showPopueWindow() {
        View popView=View.inflate(this,R.layout.popupwindow_add_task,null);
        et_pop_add_task=popView.findViewById(R.id.et_pop_add_task);
        sp_pop_task_hour=popView.findViewById(R.id.sp_pop_task_hour);
        sp_pop_task_min=popView.findViewById(R.id.sp_pop_task_min);
        mc_pop_add_task=popView.findViewById(R.id.mc_pop_add_task);
        confirm_pop_add_task=popView.findViewById(R.id.confirm_pop_add_task);

        mc_pop_add_task.setOnItemCliclListener(this);
        dateList.clear();

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        sp_pop_task_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SELECT_HOUR=AddTaskActivity.this.getResources().getStringArray(R.array.hour)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_pop_task_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SELECT_TIME=AddTaskActivity.this.getResources().getStringArray(R.array.min)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //确认点击事件
        confirm_pop_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TASK_DESCRIBE=et_pop_add_task.getText().toString();
                if(TASK_DESCRIBE==null){
                    Toast.makeText(AddTaskActivity.this, "123123", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                    return;
                }
                mTaskList=new ArrayList<>();

                for(int i=0;i<dateList.size();i++){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateD = sdf.format(dateList.get(i));
                    Task task=new Task();
                    task.date=dateD;
                    task.task=TASK_DESCRIBE;
                    task.time=SELECT_HOUR+"-"+SELECT_TIME;
                    mTaskList.add(task);
                }

                taskDao=new TaskDao(AddTaskActivity.this);
                taskDao.save(mTaskList);
                List<Task> task=taskDao.findAll();

                Toast.makeText(AddTaskActivity.this, "123"+task, Toast.LENGTH_SHORT).show();

                //在列表中添加任务，并展示
                //并且为每个任务设置闹钟
                for(int i=0;i<mTaskList.size();i++){
                    String UUID=(mTaskList.get(i).date).substring(5,7)
                            + (mTaskList.get(i).date).substring(8,10)
                            + SELECT_HOUR+SELECT_TIME;

                    int uuid=Integer.parseInt(UUID);
                    adapter.addData(tasklist.size(),mTaskList.get(i));

                    //闹钟设置
                    setSystemAlarmClock(mTaskList.get(i).task,
                            Integer.parseInt((mTaskList.get(i).date).substring(0,4)),
                            Integer.parseInt((mTaskList.get(i).date).substring(5,7)),
                            Integer.parseInt((mTaskList.get(i).date).substring(8,10)),
                            Integer.parseInt(SELECT_HOUR),Integer.parseInt(SELECT_TIME),uuid);
                }

                popupWindow.dismiss();
            }
        });

        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.75f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);

    }

    public void setSystemAlarmClock(String message, int year, int month, int day, int hour, int minute,int uuid) {
        Log.i("00000","123"+year+":"+month+":"+day+"   "+hour+"-"+minute);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month-1);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(AddTaskActivity.this, AlarmReceiver.class);
        intent.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,uuid, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        Log.i("00000,123123","12312312312");
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    @Override
    public void OnItemClick(View view, Date day) {
        if(dateList.size()==0){
            dateList.add(day);
            view.setBackgroundColor(Color.RED);
        }else{
            size=dateList.size();
            for(int i=0;i<size;i++){
                if(dateList.get(i)==day){
                    flag=true;
                    view.setBackgroundColor(Color.WHITE);
                    dateList.remove(dateList.get(i));
                    size=size-1;
                }
            }
            if(flag==true){
                flag=false;
            }else {
                dateList.add(day);
                view.setBackgroundColor(Color.RED);
            }
        }
        Toast.makeText(AddTaskActivity.this, "123"+dateList, Toast.LENGTH_SHORT).show();
    }


    /**
     * 得到当前日期数据
     */
    private void getDateTime() {
        Intent intent=getIntent();
        DEFAULT_TIME=intent.getStringExtra("date");
        LogUtil.e("1231231"+DEFAULT_TIME);
    }





    private void initRecycler() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        tasklist=new ArrayList<>();
        adapter=new AddTaskAdapter(AddTaskActivity.this,tasklist,taskDao);
        mRecyclerView.setAdapter(adapter);
        //   添加动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initView() {
        rl_add=findViewById(R.id.rl_add);
        mRecyclerView=findViewById(R.id.addtask_recyclerview);
        ll_acty_layout=findViewById(R.id.ll_acty_layout);
    }
}
