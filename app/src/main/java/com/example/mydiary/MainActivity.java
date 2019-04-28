package com.example.mydiary;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText et_password;
    private Button btn_login;

    private SQLiteDatabase db;
    private String db_name = "myDiary";
    private String tb_name = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_password = (EditText) findViewById(R.id.main_password);
        btn_login = (Button) findViewById(R.id.btn_main_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPass("123");
                String password = et_password.getText().toString();

                if (!password.equals("")) {
                    db = openOrCreateDatabase(db_name,MODE_PRIVATE,null);
                    Cursor cursor = db.rawQuery("select * from " + tb_name,null);

                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            if (!password.equals(cursor.getString(cursor.getColumnIndex("pass")))) {
                                Toast.makeText(MainActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                                break;
                            }else {
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, WriteDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                                startActivity(intent);

                                Toast.makeText(MainActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

    }
    private void addPass(String pass){
        db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name
                + "(pass VARCHAR(50))";
        db.execSQL(createTable);

        ContentValues cv = new ContentValues(7);
        cv.put("pass", pass);

        db.insert(tb_name, null, cv);

    }

}
