package com.example.mydiary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 初中生 on 2018/12/5.
 */
public class WriteDiaryActivity extends Activity {
    private MyTopview myTopview;
    private MyDate2 myDate2;

    //weather droplist
    private ImageView imgDrop_weather;
    private ListView lvList_weather;
    private PopupWindow mPopup_weather;
    public ArrayList<Integer> popup_images_weather;

    //feeling droplist
    private ImageView imgDrop_feeling;
    private ListView lvList_feeling;
    private PopupWindow mPopup_feeling;
    public ArrayList<Integer> popup_images_feeling;

    //content
    private EditText et_title;
    private EditText et_content;
    //private EditText et_showcontent;
    private Button btn_addiary;
    //private Button btn_showdiary;

    String filePath = Environment.getExternalStorageDirectory() + "/save/";
    String fileName = "save.txt";

    private SQLiteDatabase db;
    private String db_name = "myDiary";
    private String tb_name = "diary";

    String month, day, week;
    private int weather_pos, feeling_pos;

    @TargetApi(24)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writediary);

        addRecord("December", "15", "Saturday", "1", "0", "Suceess", "MyDiary is built!");

        //startActivity(new Intent(this, BrowseDiaryActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        getWindow().setEnterTransition(new Slide().setDuration(2000));
        getWindow().setExitTransition(new Slide().setDuration(2000));

        //TOPVIEW
        myTopview = (MyTopview) findViewById(R.id.wd_mytopview);
        myTopview.setTop_leftText("查看日记");
        myTopview.setTop_centerText("写日记");
        myTopview.setTop_rightText("个人中心");

       // myTopview.setTop_centerBackgroundColor(R.color.lightblue);

        myTopview.setOnclickTopLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了左边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(WriteDiaryActivity.this, BrowseDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);

            }
        });

        myTopview.setOnclickTopCenter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了中间按钮", Toast.LENGTH_SHORT).show();

            }
        });

        myTopview.setOnclickTopRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了右边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(WriteDiaryActivity.this, PersonalCenterActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });



        Calendar calendar = Calendar.getInstance();
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                week = "Sunday";
                break;
            case Calendar.MONDAY:
                week = "Monday";
                break;
            case Calendar.TUESDAY:
                week = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                week = "Wednesday";
                break;
            case Calendar.THURSDAY:
                week = "Thursday";
                break;
            case Calendar.FRIDAY:
                week = "Friday";
                break;
            case Calendar.SATURDAY:
                week = "Saturday";
                break;
            default:
                break;
        }


        JSONObject Month = new JSONObject();
        try {
            Month.put("1", "January");
            Month.put("2", "February");
            Month.put("3", "March");
            Month.put("4", "April");
            Month.put("5", "May");
            Month.put("6", "June");
            Month.put("7", "July");
            Month.put("8", "August");
            Month.put("9", "September");
            Month.put("10", "October");
            Month.put("11", "November");
            Month.put("12", "December");

            month = Month.getString(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //MYDATE
        myDate2 = (MyDate2) findViewById(R.id.wd_mydate2);
        myDate2.setTv_month(month);
        myDate2.setTv_day(day);
        myDate2.setTv_week(week);

        //weather droplist
        imgDrop_weather = (ImageView) findViewById(R.id.image_wd_weather);
        imgDrop_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weather_showDropDown();
            }
        });
        weather_Drop();

        //feeling droplist
        imgDrop_feeling = (ImageView) findViewById(R.id.image_wd_feeling);
        imgDrop_feeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling_showDropDown();
            }
        });
        feeling_Drop();

        //content save and show
        et_content = (EditText) findViewById(R.id.et_wd_content);
        //et_showcontent = (EditText) findViewById(R.id.et_wd_showcontent);
        btn_addiary = (Button) findViewById(R.id.btn_wd_addiary);
        //btn_showdiary = (Button) findViewById(R.id.btn_wd_showdiary);

        et_title = findViewById(R.id.tv_wd_title);

        btn_addiary.setOnClickListener(listener);
        //btn_showdiary.setOnClickListener(listener);
    }

    //初始化天气listview 作为popupwindow的视图
    private void weather_Drop(){
        lvList_weather = new ListView(this);

        popup_images_weather = new ArrayList();
        popup_images_weather.add(R.drawable.sunny);
        popup_images_weather.add(R.drawable.cloudy);
        popup_images_weather.add(R.drawable.rainy);

        lvList_weather.setAdapter(new weather_MyAdapter());
        lvList_weather.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgDrop_weather.setImageResource(popup_images_weather.get(position));
                mPopup_weather.dismiss();
                weather_pos = position;
            }
        });
    }

    //weather下拉框的显示
    protected void weather_showDropDown(){
        if (mPopup_weather == null){
            mPopup_weather = new PopupWindow(lvList_weather,170,800,true);
            mPopup_weather.setFocusable(true);
            mPopup_weather.setOutsideTouchable(true);
            mPopup_weather.setBackgroundDrawable(new ColorDrawable());
        }

        mPopup_weather.showAsDropDown(imgDrop_weather);
    }

    //weather ListView的适配器
    class weather_MyAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return popup_images_weather.size();
        }

        @Override
        public Object getItem(int position) {
            return popup_images_weather.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.my_imagelist, null);

                holder = new ViewHolder();
                holder.image_list_weather = (ImageView) convertView.findViewById(R.id.image_list);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image_list_weather.setImageResource(popup_images_weather.get(position));

            return convertView;
        }

    }


    //初始化心情listview 作为popupwindow的视图
    private void feeling_Drop(){
        lvList_feeling = new ListView(this);

        popup_images_feeling = new ArrayList();
        popup_images_feeling.add(R.drawable.happy);
        popup_images_feeling.add(R.drawable.soso);
        popup_images_feeling.add(R.drawable.sad);

        lvList_feeling.setAdapter(new feeling_MyAdapter());
        lvList_feeling.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgDrop_feeling.setImageResource(popup_images_feeling.get(position));
                mPopup_feeling.dismiss();
                feeling_pos = position;
            }
        });
    }

    //feeling下拉框的显示
    protected void feeling_showDropDown(){
        if (mPopup_feeling == null){
            mPopup_feeling = new PopupWindow(lvList_feeling,170,800,true);
            mPopup_feeling.setFocusable(true);
            mPopup_feeling.setOutsideTouchable(true);
            mPopup_feeling.setBackgroundDrawable(new ColorDrawable());
        }

        mPopup_feeling.showAsDropDown(imgDrop_feeling);
    }

    //feeling ListView的适配器
    class feeling_MyAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return popup_images_feeling.size();
        }

        @Override
        public Object getItem(int position) {
            return popup_images_feeling.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.my_imagelist, null);

                holder = new ViewHolder();
                holder.image_list_feeling = (ImageView) convertView.findViewById(R.id.image_list);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image_list_feeling.setImageResource(popup_images_feeling.get(position));

            return convertView;
        }

    }

    static class ViewHolder {
        public ImageView image_list_weather;
        public ImageView image_list_feeling;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button view = (Button) v;
            switch (view.getId()){
                case R.id.btn_wd_addiary:
                    // writeTxtToFile(tv_content.getText().toString().trim(), filePath, fileName);
                    save();
                    //Toast.makeText(WriteDiaryActivity.this, "save", Toast.LENGTH_LONG).show();

                    break;

               /* case R.id.btn_wd_showdiary:
                    //showFileToTxt(tv_content.getText().toString().trim(), filePath, fileName);
                    read();
                    Toast.makeText(WriteDiaryActivity.this, "show", Toast.LENGTH_LONG).show();
                    break;*/
            }
        }
    };

    @TargetApi(24)
    private void save() {

        BufferedWriter bufferdWriter = null;
        String content = et_content.getText().toString();
        String title = et_title.getText().toString();

        /*fileName = month + "_" + day + "_" + fileName;
        try {
            FileOutputStream outputStream = openFileOutput(fileName, Activity.MODE_PRIVATE);
            bufferdWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferdWriter.write(content);
            Toast.makeText(WriteDiaryActivity.this, "保存成功", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
                 e.printStackTrace();
        } finally {
            try {
                if (bufferdWriter != null) {
                    bufferdWriter.close();
                }
            } catch (IOException e) {
                 e.printStackTrace();
            }
        }*/

        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name
                + "(month VARCHAR(10),"
                + "day VARCHAR(2),"
                + "week VARCHAR(10),"
                + "weather VARCHAR(255),"
                + "feeling VARCHAR(255),"
                + "title VARCHAR(255),"
                + "content VARCHAR(255))";
        db.execSQL(createTable);

        String weather = String.valueOf(weather_pos);
        String feeling = String.valueOf(feeling_pos);

        if (!content.equals("") && !title.equals("")){
            addDiaryData(month, day, week, weather, feeling, title, content);
            Toast.makeText(WriteDiaryActivity.this, "添加成功", Toast.LENGTH_LONG).show();
            et_title.setText("");
            et_content.setText("");
        }
        else {
            Toast.makeText(WriteDiaryActivity.this, "请输入标题和内容", Toast.LENGTH_LONG).show();
        }

    }

    private void addDiaryData(String month, String day, String week, String weather, String feeling,
                              String title, String content) {
        ContentValues cv = new ContentValues(7);
        cv.put("month", month);
        cv.put("day", day);
        cv.put("week", week);
        cv.put("weather", weather);
        cv.put("feeling", feeling);
        cv.put("title", title);
        cv.put("content", content);

        db.insert(tb_name, null, cv);
    }

    private void addRecord(String month, String day, String week, String weather, String feeling,
                           String title, String content){
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name
                + "(month VARCHAR(10),"
                + "day VARCHAR(2),"
                + "week VARCHAR(10),"
                + "weather VARCHAR(255),"
                + "feeling VARCHAR(255),"
                + "title VARCHAR(255),"
                + "content VARCHAR(255))";
        db.execSQL(createTable);

        ContentValues cv = new ContentValues(7);
        cv.put("month", month);
        cv.put("day", day);
        cv.put("week", week);
        cv.put("weather", weather);
        cv.put("feeling", feeling);
        cv.put("title", title);
        cv.put("content", content);

        db.insert(tb_name, null, cv);
    }

    //读取刚才用户保存的内容

    /*private void read() {
        BufferedReader bufferedReader = null;

        try {
             FileInputStream inputStream = this.openFileInput(fileName);
             bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
             StringBuilder stirngBuidler = new StringBuilder();
             String line = "";
             while ((line = bufferedReader.readLine()) != null) {
                     stirngBuidler.append(line);
                 }
                et_showcontent.setText(stirngBuidler.toString());
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
             try {
                 if (bufferedReader != null) {
                    bufferedReader.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
        }
    }
*/


   /* // 将字符串写入到文本文件中
    private void writeTxtToFile(String strcontent, String filePath, String fileName) {

        // 生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath,fileName);

        String  strFilePath = filePath + fileName;

        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}
