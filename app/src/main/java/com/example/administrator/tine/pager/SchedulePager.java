package com.example.administrator.tine.pager;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;



import androidx.viewpager.widget.ViewPager;

import com.example.administrator.tine.R;
import com.example.administrator.tine.base.BasePager;
import com.example.administrator.tine.pager.adapter.ScheduleDetailAdapter;
import com.example.administrator.tine.pager.fragment.ListFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchedulePager extends BasePager {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ScheduleDetailAdapter adapter;
    private List<ListFragment> listFragments;

    public SchedulePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        View view= View.inflate(context,R.layout.schedule_pager,null);
        tabLayout=view.findViewById(R.id.tablayout);
        viewPager=view.findViewById(R.id.viewpager);

        initViewPager();
        fl_content_pager.addView(view);
    }

    private void initViewPager() {
        //获取最近5天时间
        String a,b,c,d,e;
        SimpleDateFormat sf=new SimpleDateFormat("MM月dd日");
        Calendar cal=Calendar.getInstance();
        a=sf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);
        b=sf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);
        c=sf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);
        d=sf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);
        e=sf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);

        List<String> titles=new ArrayList<>();
        titles.add(a);
        titles.add(b);
        titles.add(c);
        titles.add(d);
        titles.add(e);
        for(int i=0;i<titles.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }

        listFragments=new ArrayList<>();

        for(int i=0;i<titles.size();i++){
            listFragments.add(new ListFragment(context));
        }


        adapter=new ScheduleDetailAdapter(context,titles,listFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
