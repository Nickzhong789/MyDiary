package com.example.mydiary;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Nick on 2018/12/14.
 */
public class myCard2 extends RelativeLayout {
    private TextView tv_card_month;
    private TextView tv_card_day;
    private TextView tv_card_week;
    private TextView tv_card_title;
    private TextView tv_card_content;

    private ImageView image_card_weather;
    private ImageView image_card_feeling;

    private int weatherSrc, feelingSrc;

    private String month, day, week, title, content;

    private LayoutParams tm_params, td_params, tw_params, tt_params, tc_params, iw_params, if_params;


    public myCard2(Context context) {
        this(context, null);

        tv_card_month = new TextView(context);
        tv_card_month.setText(month);
        tv_card_month.setTextSize(20);
        tv_card_month.setTextColor(Color.parseColor("#87CEFA"));

        tv_card_day = new TextView(context);
        tv_card_day.setText(day);
        tv_card_day.setTextSize(40);
        tv_card_day.setTextColor(Color.parseColor("#87CEFA"));

        tv_card_week = new TextView(context);
        tv_card_week.setText(week);
        tv_card_week.setTextSize(15);
        tv_card_week.setTextColor(Color.parseColor("#808080"));

        tv_card_title = new TextView(context);
        tv_card_title.setText(title);
        tv_card_title.setTextSize(20);
        tv_card_title.setTextColor(Color.parseColor("#87CEFA"));

        tv_card_content = new TextView(context);
        tv_card_content.setText(content);
        tv_card_content.setTextSize(15);
        tv_card_content.setTextColor(Color.parseColor("#808080"));

        image_card_weather = new ImageView(context);
        setImage_card_weather(weatherSrc);

        image_card_feeling = new ImageView(context);
        setImage_card_feeling(feelingSrc);

        iw_params = new LayoutParams(100, 100);
        iw_params.topMargin = 50;
        iw_params.leftMargin = 750;
        addView(image_card_weather, iw_params);

        if_params = new LayoutParams(100, 100);
        if_params.topMargin = 50;
        if_params.leftMargin = 870;
        addView(image_card_feeling, if_params);

        setBackgroundColor(Color.parseColor("#FFFFF0"));

    }

    public myCard2(Context context, AttributeSet attrs) {
        super(context, null);
    }

    public void setMonth(String month){
        removeView(tv_card_month);
        tv_card_month.setText(month);
        tm_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tm_params.leftMargin = 50;
        tm_params.topMargin = 50;
        addView(tv_card_month, tm_params);
    }

    public void setDay(String day){
        removeView(tv_card_day);
        tv_card_day.setText(day);
        td_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        td_params.leftMargin = 50;
        td_params.topMargin = 100;
        //td_params.addRule(RelativeLayout.BELOW, );
        addView(tv_card_day, td_params);
    }

    public void setWeek(String week){
        removeView(tv_card_week);
        tv_card_week.setText(week);
        tw_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tw_params.leftMargin = 50;
        tw_params.topMargin = 250;
        addView(tv_card_week, tw_params);
    }

    public void setTitle(String title){
        removeView(tv_card_title);
        tv_card_title.setText(title);
        tt_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tt_params.leftMargin = 350;
        tt_params.topMargin = 50;
        addView(tv_card_title, tt_params);
    }

    public void setContent(String content){
        removeView(tv_card_content);
        tv_card_content.setText(content);
        tc_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tc_params.leftMargin = 350;
        tc_params.topMargin = 150;
        addView(tv_card_content, tc_params);
    }

    public void setImage_card_weather(int weatherSrc){
        image_card_weather.setImageResource(weatherSrc);
    }

    public void setImage_card_feeling(int feelingSrc){
        image_card_feeling.setImageResource(feelingSrc);
    }
}
