package com.example.administrator.tine.base;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.tine.R;

/**
 * 各个子页面的基类
 */
public class BasePager {
    public final Context context;//上下文ContentActivity
    public FrameLayout fl_content_pager;

    public View rootView;

    public BasePager(Context context) {
        this.context=context;
        rootView=initView();
    }

    private View initView() {
        View view=View.inflate(context,R.layout.base_pager,null);
        fl_content_pager=view.findViewById(R.id.fl_content_pager);
        return view;
    }


    public void initData(){
    }

}
