package com.example.administrator.tine.pager;

import android.app.Activity;
import android.view.View;

import com.example.administrator.tine.R;
import com.example.administrator.tine.base.BasePager;

public class SettingPager extends BasePager {
    public SettingPager(Activity context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        View view= View.inflate(context,R.layout.setting_pager,null);

        fl_content_pager.addView(view);

    }
}
