package com.example.administrator.tine.pager.fragment;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.administrator.tine.R;
import com.example.administrator.tine.bean.Databean;



import com.example.administrator.tine.pager.base.BasePager;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BasePager {

    private RecyclerView mRecyclerView;
    private SchedulePagerAdapter mSchedulePagerAdapter;
    private String title;


    public ListFragment(Context context,String title) {
        super(context);
        this.title=title;
    }


    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public View initView() {
        View view=View.inflate(context,R.layout.schedule_recyclerview,null);
        mRecyclerView=view.findViewById(R.id.schedule_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        //从数据库获得当天的任务事件列表。title代表当天的日期

        List<Databean> list=new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            list.add(new Databean( i+":00", "25/10"));
        }
        mSchedulePagerAdapter=new SchedulePagerAdapter(context, list,title);

        mRecyclerView.setAdapter(mSchedulePagerAdapter);
        return view;
    }



}
