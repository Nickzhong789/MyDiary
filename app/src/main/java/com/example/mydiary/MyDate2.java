package com.example.mydiary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 初中生 on 2018/12/4.
 */
public class MyDate2 extends RelativeLayout
{
    private TextView tv_month;
    private TextView tv_day;
    private TextView tv_week;

    public MyDate2(Context context) {
        super(context);
    }

    public MyDate2(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.my_date,this);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_week = (TextView) findViewById(R.id.tv_week);
    }

    public void setTv_month(String month) {
        tv_month.setText(month);
    }

    public void setTv_day(String day) {
        tv_day.setText(day);
    }

    public void setTv_week(String week) {
        tv_week.setText(week);
    }
}


