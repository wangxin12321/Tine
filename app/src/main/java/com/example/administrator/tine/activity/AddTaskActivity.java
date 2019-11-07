package com.example.administrator.tine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.administrator.tine.R;
import com.example.administrator.tine.adapter.AddTaskAdapter;
import com.example.administrator.tine.bean.Databean;
import com.example.administrator.tine.db.SQLiteDBOperator;
import com.example.administrator.tine.view.SelectDateTimePopupWindow;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.tine.pager.HomePager.DATE_TIME;

public class AddTaskActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RelativeLayout rl_add;
    private List<String> tasklist;
    private AddTaskAdapter adapter;
    private LinearLayout ll_acty_layout;
    public static String DEFAULT_TIME="default_time";

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
                //添加事件
                TaskDateandTime();
                //TaskDateandTime();
                //       添加自带默认动画
                adapter.addData(tasklist.size());
            }
        });
    }

    /**
     * 得到当前日期数据
     */
    private void getDateTime() {
        Intent intent=getIntent();
        DEFAULT_TIME=intent.getStringExtra(DATE_TIME);
    }


    /**
     * 弹出时间选择框
     */
    private void TaskDateandTime() {

        final SelectDateTimePopupWindow SDT=new SelectDateTimePopupWindow(AddTaskActivity.this);
        SDT.showPopup(ll_acty_layout);
        SDT.setOnSelectDateTimePopupWindowListener(new SelectDateTimePopupWindow
                .SelectDateTimePopupWindowOnClickListener() {
            @Override
            public void obtainMessage(int flag) {
               switch (flag){
                   case 0:
                       //读取spinner，事件信息
                       Databean databean=SDT.getData();
                       //点击确定之后添加事件到本地数据库中
                       SQLiteDBOperator sqLiteDBOperator=new SQLiteDBOperator(AddTaskActivity.this);
                       sqLiteDBOperator.addData(databean);
                       //然后刷新item，添加item。item变化使用数据库的值来变化，直接链接本地数据库,再一次读取数据库

                       break;
                   default:
                       break;
               }
            }
        });

    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        tasklist=new ArrayList<>();
        adapter=new AddTaskAdapter(AddTaskActivity.this,tasklist);
        mRecyclerView.setAdapter(adapter);
//   添加动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void initView() {
        rl_add=findViewById(R.id.rl_add);
        mRecyclerView=findViewById(R.id.addtask_recyclerview);
        ll_acty_layout=findViewById(R.id.ll_acty_layout);
    }



    protected ArrayList<String> initData(){
        ArrayList<String> mDatas=new ArrayList<String>();
        for(int i=0;i<1;i++){
            mDatas.add("我是商品" + i);
        }
        return mDatas;
    }


}
