package com.example.mydiary;

import android.content.Context;

/**
 * Created by 初中生 on 2018/12/13.
 */
//对数据库操作的工具类
public class DbManager {
    private static MySqliteHelper helper;

    public static MySqliteHelper getIntance(Context context){
        if (helper == null){
            helper = new MySqliteHelper((context));
        }
        return helper;
    }

}
