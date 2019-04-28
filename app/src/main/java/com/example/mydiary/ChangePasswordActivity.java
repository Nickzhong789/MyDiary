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

/**
 * Created by 初中生 on 2018/12/11.
 */
public class ChangePasswordActivity extends Activity {
    private MyTopview myTopview;
    private Button submit;

    private SQLiteDatabase db;
    private String db_name = "myDiary";
    private String tb_name = "password";

    private EditText ev_old_pass_in;
    private EditText ev_new_pass_in;
    private EditText ev_new_pass_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        //TOPVIEW
        myTopview = (MyTopview) findViewById(R.id.d_mytopview);
        myTopview.setTop_leftText("查看日记");
        myTopview.setTop_centerText("写日记");
        myTopview.setTop_rightText("个人中心");

        myTopview.setOnclickTopLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了左边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ChangePasswordActivity.this, BrowseDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);

            }
        });

        myTopview.setOnclickTopCenter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了中间按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ChangePasswordActivity.this, WriteDiaryActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        myTopview.setOnclickTopRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WriteDiaryActivity.this, "点击了右边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ChangePasswordActivity.this, PersonalCenterActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        ev_old_pass_in = findViewById(R.id.et_cp_originalpw);
        ev_new_pass_in = findViewById(R.id.et_cp_newpw);
        ev_new_pass_confirm = findViewById(R.id.et_cp_confirmpw);
        submit = findViewById(R.id.btn_cp_ok);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_pass = ev_old_pass_in.getText().toString();
                String new_pass = ev_new_pass_in.getText().toString();
                String confirm_pass = ev_new_pass_confirm.getText().toString();

                //addPass("123");

                if (!old_pass.equals("") && !new_pass.equals("") && !confirm_pass.equals("")){
                    db = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
                    Cursor cursor = db.rawQuery("select * from " + tb_name, null);
                    if (cursor != null){
                        while (cursor.moveToNext()){
                            if (!old_pass.equals(cursor.getString(cursor.getColumnIndex("pass")))){
                                Toast.makeText(ChangePasswordActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else if (!new_pass.equals(confirm_pass)){
                                Toast.makeText(ChangePasswordActivity.this, "两次密码不匹配", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else if (old_pass.equals(new_pass)){
                                Toast.makeText(ChangePasswordActivity.this, "旧密码与新密码相同", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else {
                                String update_sql = "update " + tb_name + " set pass = '" + new_pass + "'";
                                db.execSQL(update_sql);
                                Toast.makeText(ChangePasswordActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();

                                ev_old_pass_in.setText("");
                                ev_new_pass_in.setText("");
                                ev_new_pass_confirm.setText("");
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, "输入信息不完整", Toast.LENGTH_SHORT).show();
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
