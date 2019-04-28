package com.example.mydiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by 初中生 on 2018/12/5.
 */
public class BrowseDiaryActivity extends Activity {
    private MyTopview myTopview;

    private String db_name = "myDiary";
    private String tb_name = "diary";
    private SQLiteDatabase db;

    private LinearLayout lin;

    public ArrayList<Integer> weather_list, feeling_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsediary);

        lin = findViewById(R.id.lin);
        //TOPVIEW
        myTopview = findViewById(R.id.bd_mytopview);
        myTopview.setTop_leftText("查看日记");
        myTopview.setTop_centerText("写日记");
        myTopview.setTop_rightText("个人中心");

        // myTopview.setTop_centerBackgroundColor(R.color.lightblue);

        myTopview.setOnclickTopLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了左边按钮", Toast.LENGTH_SHORT).show();


            }
        });

        myTopview.setOnclickTopCenter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了中间按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(BrowseDiaryActivity.this, WriteDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);

            }
        });

        myTopview.setOnclickTopRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了右边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(BrowseDiaryActivity.this, PersonalCenterActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        weather_list = new ArrayList<>();
        weather_list.add(R.drawable.sunny);
        weather_list.add(R.drawable.cloudy);
        weather_list.add(R.drawable.rainy);

        feeling_list = new ArrayList<>();
        feeling_list.add(R.drawable.happy);
        feeling_list.add(R.drawable.soso);
        feeling_list.add(R.drawable.sad);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("select * from " + tb_name, null);
        if (cursor != null) {
            while (cursor.moveToNext()){
                String month = cursor.getString(cursor.getColumnIndex("month"));
                String day = cursor.getString(cursor.getColumnIndex("day"));
                String week = cursor.getString(cursor.getColumnIndex("week"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                //String content_path = cursor.getString(cursor.getColumnIndex("content"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                int feeling = Integer.parseInt(cursor.getString(cursor.getColumnIndex("feeling")));
                int weather = Integer.parseInt(cursor.getString(cursor.getColumnIndex("weather")));


                final myCard2 myCard= new myCard2(BrowseDiaryActivity.this);
                myCard.setMonth(month);
                myCard.setDay(day);
                myCard.setWeek(week);
                myCard.setTitle(title);
                myCard.setContent(content);
                myCard.setImage_card_feeling(feeling_list.get(feeling));
                myCard.setImage_card_weather(weather_list.get(weather));

                myCard.getBackground().setAlpha(200);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350);
                layoutParams.topMargin = 50;
                lin.addView(myCard, layoutParams);
            }
            cursor.close();
        }

    }

    private String getContent(String fileName) {
//        BufferedReader bufferedReader = null;
//        String content = "";
//        try {
//            FileInputStream inputStream = new FileInputStream(fileName);
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder stirngBuidler = new StringBuilder();
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                stirngBuidler.append(line);
//            }
//            content = stirngBuidler.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        String content = "";

        fileName= "/"+ fileName;
        try {
            File f=new File(Environment.getExternalStorageDirectory().getPath()+fileName);
            int length=(int)f.length();
            byte[] buff=new byte[length];
            FileInputStream fin=new FileInputStream(f);
            fin.read(buff);
            fin.close();
            content = new String(buff,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(BrowseDiaryActivity.this,Environment.getExternalStorageDirectory().getPath()+fileName,Toast.LENGTH_SHORT).show();
        }
        return content;
    }
}
