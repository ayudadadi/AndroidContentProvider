package com.example.yls.demob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yls on 2018/12/3.
 */

public class StuOpenHelper extends SQLiteOpenHelper {
    public StuOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "create table student (stuno varchar(20), name varchar(20), age int)";
        sqLiteDatabase.execSQL(sql1);

        String sql2 = "create table score (stuno varchar(20), english int, android int)";
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
