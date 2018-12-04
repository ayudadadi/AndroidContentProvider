package com.example.yls.demob;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by yls on 2018/12/3.
 */

public class StuContentProvider extends ContentProvider {
    private StuOpenHelper openHelper;
    private static final String AUTOHORITY = "com.gdcp.student";
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTOHORITY, "student", 1);
        uriMatcher.addURI(AUTOHORITY, "score", 2);
    }

    @Override
    public boolean onCreate() {
        openHelper = new StuOpenHelper(getContext(),"stu.db",null, 1);
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case 1:
                sqLiteDatabase.insert("student", null, contentValues);
                break;

            case 2:
                sqLiteDatabase.insert("score", null, contentValues);
                break;
        }

        sqLiteDatabase.close();
        return null;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy) {

        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)){
            case 1:
                Cursor cursor1 = sqLiteDatabase.query("student", projection, selection,selectionArgs,null, null, orderBy);
                return cursor1;

            case 2:
                Cursor cursor2 = sqLiteDatabase.query("score", projection, selection,selectionArgs,null, null, orderBy);
                return cursor2;

        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        switch (uriMatcher.match(uri)){
            case 1:
                SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
                int rows = sqLiteDatabase.delete("student",s,strings);
                sqLiteDatabase.close();
                return rows;
            case 2:
                break;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        switch (uriMatcher.match(uri)){
            case 1:
                SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
                int rows = sqLiteDatabase.update("student",contentValues,s,strings);
                sqLiteDatabase.close();
                return rows;

        }
        return 0;
    }
}
