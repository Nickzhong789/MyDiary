package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 初中生 on 2018/12/13.
 */
public class MySqliteHelper extends SQLiteOpenHelper {


    public MySqliteHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context, MyContant.DATABASE_NAME, null, MyContant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","------onCreate------");
        System.out.println("------onCreate------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("tag","------onUpgrade------");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i("tag","------onOpen------");

    }
}
