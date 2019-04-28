package com.example.mydiary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 初中生 on 2018/12/5.
 */
public class MyCard extends RelativeLayout {
    private TextView tv_card_month;
    private TextView tv_card_day;
    private TextView tv_card_week;
    private TextView tv_card_title;
    private TextView tv_card_article;
    private ImageView image_card_weather;
    private ImageView image_card_feeling;

    public MyCard(Context context) {
        super(context);
    }

    public MyCard(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.my_card,this);

        tv_card_month = (TextView) findViewById(R.id.tv_card_month);
        tv_card_day = (TextView) findViewById(R.id.tv_card_day);
        tv_card_week = (TextView) findViewById(R.id.tv_card_week);
        tv_card_title = (TextView) findViewById(R.id.tv_card_title);
        tv_card_article = (TextView) findViewById(R.id.tv_card_article);
        image_card_weather = (ImageView) findViewById(R.id.image_card_weather);
        image_card_feeling = (ImageView) findViewById(R.id.image_card_feeling);
    }

    public void setTv_card_month(String month){
        tv_card_month.setText(month);
    }

    public void setTv_card_day(String day){
        tv_card_day.setText(day);
    }

    public void setTv_card_week(String week){
        tv_card_week.setText(week);
    }

    public void setTv_card_title(String title){
        tv_card_title.setText(title);
    }

    public void setTv_card_article(String article){
        tv_card_article.setText(article);
    }

    public void setImage_card_weather(int weatherSrc){
        image_card_weather.setImageResource(weatherSrc);
    }

    public void setImage_card_feeling(int feelingSrc){
        image_card_feeling.setImageResource(feelingSrc);
    }
}
