package com.example.administrator.tine.view.cal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MyCalTextView extends AppCompatTextView {
    private Paint paint;
    public boolean isToday=false;
    public MyCalTextView(Context context) {
        this(context,null);
    }
    public MyCalTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MyCalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        if(isToday) {
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
            canvas.drawCircle(0, 0, getHeight() / 2-8, paint);
        }

    }
}
