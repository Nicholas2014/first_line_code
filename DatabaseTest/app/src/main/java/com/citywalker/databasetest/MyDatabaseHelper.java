package com.citywalker.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/9.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context _context;
    private static final String CREATE_BOOK_SQL = "create table book (" +
            "int integer primary key autoincrement, " +
            "author text, " +
            "price real, " +
            "pages int, " +
            "name text)";

    private static final String CREATE_CATEGORY_SQL = "create table category (" +
            "id integer primary key autoincrement, " +
            "category_name text, " +
            "category_code integer)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_SQL);
        db.execSQL(CREATE_CATEGORY_SQL);
        Toast.makeText(_context, "create table succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists category");
        onCreate(db);
    }
}
