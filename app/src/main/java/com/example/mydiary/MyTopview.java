package com.example.mydiary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by 初中生 on 2018/12/4.
 */
public class MyTopview extends RelativeLayout {

    private Button top_left;
    private Button top_center;
    private Button top_right;

    public MyTopview(Context context) {
        super(context);
    }

    public MyTopview(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.my_topview,this);

        top_left = (Button) findViewById(R.id.btn_top_left);
        top_center = (Button) findViewById(R.id.btn_top_center);
        top_right = (Button) findViewById(R.id.btn_top_right);
    }

    public void setTop_leftText(String text){
        top_left.setText(text);
    }

    public void setTop_centerText(String text){
        top_center.setText(text);
    }

    public void setTop_rightText(String text){
        top_right.setText(text);
    }

    public void setTop_leftBackgroundColor(int color){
        top_left.setBackgroundColor(color);
    }

    public void setTop_centerBackgroundColor(int color){
        top_left.setBackgroundColor(color);
    }

    public void setTop_rightBackgroundColor(int color){
        top_left.setBackgroundColor(color);
    }

    public void setOnclickTopLeft(OnClickListener listener){
        top_left.setOnClickListener(listener);
    }

    public void setOnclickTopCenter(OnClickListener listener){
        top_center.setOnClickListener(listener);
    }

    public void setOnclickTopRight(OnClickListener listener){
        top_right.setOnClickListener(listener);
    }
}
