package com.example.administrator.tine.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义不可滑动的Viewpager
 */
public class NoSrcollViewPager extends ViewPager {
    /**
     * 在代码中实例化时用这个方法
     * @param context
     */
    public NoSrcollViewPager(@NonNull Context context) {
        super(context);
    }

    /**
     * 在布局文件中使用该类的时候，实例化该类用构造方法，这个方法不能少，少的话会崩溃
     * @param context
     * @param attrs
     */
    public NoSrcollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 消耗掉
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * @param ev
     * @return
     * 返回false，不消耗事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
