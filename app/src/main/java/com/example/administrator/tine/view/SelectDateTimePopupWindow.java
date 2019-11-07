package com.example.administrator.tine.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.tine.R;
import com.example.administrator.tine.bean.Databean;
import com.example.administrator.tine.bean.DayFinish;
import com.example.administrator.tine.pager.HomePager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.administrator.tine.activity.AddTaskActivity.DEFAULT_TIME;


public class SelectDateTimePopupWindow implements View.OnClickListener {

    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;

    public String TASK_DATE;
    public String TASK_TIME_HOUR;
    public String TASK_TIME_MIN;
    public String TASK_NAME;

    private Spinner select_hour;
    private Spinner select_min;

    private Button btn_confirm;
    private TextView task_name;
    private CustomCalendar add_task_cal;

    public PopupWindow popupWindow;

    private Context context;
    private SelectDateTimePopupWindowOnClickListener SelectDateTimePopupWindowListener;



    public SelectDateTimePopupWindow(Context context) {
        this.context=context;
        popupWindow=new PopupWindow(context);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        //外部点击
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.AnimBottom);
        //设置视图
        popupWindow.setContentView(initView());
        //设置弹出界面的点击事件，即弹出与点击隐藏
        popupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.setFocusable(false);
                popupWindow.dismiss();
                return true;
            }
        });
    }

    /**
     * @return
     * 初始化控件
     */
    private View initView() {
        View view= LayoutInflater.from(context).inflate(R.layout.select_date_time_popwindow,null);
        add_task_cal=view.findViewById(R.id.add_task_cal);
        btn_confirm=view.findViewById(R.id.btn_confirm);
        select_hour=view.findViewById(R.id.select_hour);
        select_min=view.findViewById(R.id.select_min);
        task_name=view.findViewById(R.id.task_name);
        btn_confirm.setOnClickListener(this);
        initSpinnerHM();
        initCal();
        return view;
    }


    private void initCal() {
        //将点击进入的那一天设置为默认日期

//4.绑定数据
        //TODO 模拟请求当月数据
        final List<DayFinish> list = new ArrayList<>();
        list.add(new DayFinish(1,2,2));
        list.add(new DayFinish(2,1,2));
        list.add(new DayFinish(3,0,2));
        list.add(new DayFinish(4,2,2));
        list.add(new DayFinish(5,2,2));
        list.add(new DayFinish(6,2,2));
        list.add(new DayFinish(7,2,2));
        list.add(new DayFinish(8,0,2));
        list.add(new DayFinish(9,1,2));
        list.add(new DayFinish(10,2,2));
        list.add(new DayFinish(11,5,2));
        list.add(new DayFinish(12,2,2));
        list.add(new DayFinish(13,2,2));
        list.add(new DayFinish(14,3,2));
        list.add(new DayFinish(15,2,2));
        list.add(new DayFinish(16,1,2));
        list.add(new DayFinish(17,0,2));
        list.add(new DayFinish(18,2,2));
        list.add(new DayFinish(19,2,2));
        list.add(new DayFinish(20,0,2));
        list.add(new DayFinish(21,2,2));
        list.add(new DayFinish(22,1,2));
        list.add(new DayFinish(23,2,0));
        list.add(new DayFinish(24,0,2));
        list.add(new DayFinish(25,2,2));
        list.add(new DayFinish(26,2,2));
        list.add(new DayFinish(27,2,2));
        list.add(new DayFinish(28,2,2));
        list.add(new DayFinish(29,2,2));
        list.add(new DayFinish(30,2,2));
        list.add(new DayFinish(31,2,2));


        add_task_cal.setRenwu(DEFAULT_TIME,list);
        add_task_cal.setOnClickListener(new CustomCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {

            }

            @Override
            public void onRightRowClick() {

            }

            @Override
            public void onTitleClick(String monthStr, Date month) {

            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {

            }

            @Override
            public void onDayClick(int day, String dayStr, DayFinish finish) {
            }
        });
    }

    /**
     * 设置小时数和分钟数
     */
    private void initSpinnerHM() {
        String [] hour=new String[24];
        String [] min=new String[60];
        for(int i=1;i<=24;i++){
            if(i<10){
                min[i]="0"+i;
            }else {
                min[i]= String.valueOf(i);
            }
        }

        for(int j=0;j<60;j++){
            if(j<10){
                min[j]="0"+j;
            }else {
                min[j]= String.valueOf(j);
            }
        }

        //给spinner设置适配器
        adapter1=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,hour);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_hour.setAdapter(adapter1);
        //默认选中第一个
        select_hour.setSelection(0,true);
        select_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TASK_TIME_HOUR=select_hour.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        adapter2=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,min);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_min.setAdapter(adapter2);
        select_min.setSelection(0,true);
        select_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TASK_TIME_MIN=select_hour.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public Databean getData(){
        Databean databean=new Databean();
        databean.setContent(TASK_NAME);
        databean.setDate(TASK_DATE);
        databean.setTime(TASK_TIME_HOUR+"-"+TASK_TIME_MIN);
        return databean;
    }


    /**
     * 显示弹出窗口
     * @param rootView
     */
    public void showPopup(View rootView) {
        // 第一个参数是要将PopupWindow放到的View，第二个参数是位置，第三第四是偏移值
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
    }

    /**
     * @param view
     * 设置弹出窗口的点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                SelectDateTimePopupWindowListener.obtainMessage(0);

                break;
                default:
                    break;
        }
        dismiss();
    }

    /**
     * 接口
     */
    public interface SelectDateTimePopupWindowOnClickListener {
        void obtainMessage(int flag);
    }

    public void setOnSelectDateTimePopupWindowListener(SelectDateTimePopupWindowOnClickListener l) {
        this.SelectDateTimePopupWindowListener = l;
    }

    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }


}
