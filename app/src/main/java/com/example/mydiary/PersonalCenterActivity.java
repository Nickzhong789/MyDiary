package com.example.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 初中生 on 2018/12/11.
 */
public class PersonalCenterActivity extends Activity {
    private MyTopview myTopview;
    private Button btn_changepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesonalcenter);

        //TOPVIEW
        myTopview = (MyTopview) findViewById(R.id.pc_mytopview);
        myTopview.setTop_leftText("查看日记");
        myTopview.setTop_centerText("写日记");
        myTopview.setTop_rightText("个人中心");

       // myTopview.setTop_rightBackgroundColor(R.color.lightblue);

        myTopview.setOnclickTopLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了左边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(PersonalCenterActivity.this, BrowseDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);

            }
        });

        myTopview.setOnclickTopCenter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了中间按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(PersonalCenterActivity.this, WriteDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        myTopview.setOnclickTopRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了右边按钮", Toast.LENGTH_SHORT).show();

            }
        });

        //修改密码按钮
        btn_changepassword = (Button) findViewById(R.id.btn_pc_changepassword);
        btn_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalCenterActivity.this, ChangePasswordActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
    }
}
