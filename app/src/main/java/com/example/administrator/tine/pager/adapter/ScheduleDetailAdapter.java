package com.example.administrator.tine.pager.adapter;

import android.content.Context;


import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;


import com.example.administrator.tine.pager.fragment.ListFragment;

import java.util.List;

public class ScheduleDetailAdapter extends PagerAdapter {

    private Context context;
    private List<String> titles;
    private List<ListFragment>listFragments;
    public ScheduleDetailAdapter(Context context, List<String> titles,List<ListFragment>listFragments ) {
        this.listFragments=listFragments;
        this.context=context;
        this.titles=titles;

    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ListFragment listFragment=listFragments.get(position);
        View rootView=listFragment.rootView;
        listFragment.initData();
        container.addView(rootView);
       return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
