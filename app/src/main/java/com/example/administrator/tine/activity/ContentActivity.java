package com.example.administrator.tine.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.tine.R;
import com.example.administrator.tine.base.BasePager;
import com.example.administrator.tine.pager.HomePager;
import com.example.administrator.tine.pager.SchedulePager;
import com.example.administrator.tine.pager.SettingPager;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    private static final String CONTENT_FRAGMENT = "content_fragment";
    private FloatingActionMenu fab;
    private ViewPager cf_viewpager;
    private ArrayList<BasePager> basePagerArrayList;
    private FloatingActionButton fab_home;
    private FloatingActionButton fab_person;
    private FloatingActionButton fab_schedule;
    private FloatingActionButton fab_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        cf_viewpager=findViewById(R.id.cf_viewpager);
        fab=findViewById(R.id.fab);
        fab_home=findViewById(R.id.fab_home);
        fab_person=findViewById(R.id.fab_person);
        fab_schedule=findViewById(R.id.fab_schedule);
        fab_back=findViewById(R.id.fab_back);

        fab.setClosedOnTouchOutside(true);
        fab_home.setOnClickListener(new MyOnMenuButtonClickListener());
        fab_person.setOnClickListener(new MyOnMenuButtonClickListener());
        fab_schedule.setOnClickListener(new MyOnMenuButtonClickListener());
        fab_back.setOnClickListener(new MyOnMenuButtonClickListener());
        initData();

        cf_viewpager.setAdapter(new ContentActivityAdapter());
    }

    private void initData() {
        //初始化页面
        basePagerArrayList=new ArrayList<>();
        basePagerArrayList.add(new HomePager(this));
        basePagerArrayList.add(new SchedulePager(this));
        basePagerArrayList.add(new SettingPager(this));
    }

    private class MyOnMenuButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fab_home:
                    cf_viewpager.setCurrentItem(0);
                    break;
                case R.id.fab_schedule:
                    cf_viewpager.setCurrentItem(1);
                    break;
                case R.id.fab_person:
                    cf_viewpager.setCurrentItem(2);
                    break;

                case R.id.fab_back:
                    Toast.makeText(ContentActivity.this, "返回上一级", Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
    }

    private class ContentActivityAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return basePagerArrayList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager=basePagerArrayList.get(position);
            View rootView=basePager.rootView;//各个子页面
            basePager.initData();//实现页面的数据，调用各个页面的initData（），实际运行调用的事子类的initData（）
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }
    }




}
