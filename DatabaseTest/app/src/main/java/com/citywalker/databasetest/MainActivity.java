package com.citywalker.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btn_create_database;
    private Button btn_add_data;
    private Button btn_update_data;
    private Button btn_delete_data;
    private Button btn_query_data;

    private MyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MyDatabaseHelper(this, "bookstore.db", null, 2);

        btn_create_database = findViewById(R.id.btn_createdb);
        btn_add_data = findViewById(R.id.btn_add_data);
        btn_update_data = findViewById(R.id.btn_update_data);
        btn_delete_data = findViewById(R.id.btn_delete_data);
        btn_query_data = findViewById(R.id.btn_query_data);

        btn_create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.getWritableDatabase();
            }
        });

        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("name", "first line code");
                values.put("author", "郭霖");
                values.put("pages", 570);
                values.put("price", 56.5);
                db.insert("book", null, values);

                values.clear();

                values.put("name", "second line code");
                values.put("author", "郭霖");
                values.put("pages", 580);
                values.put("price", 58.2);
                db.insert("book", null, values);
            }
        });

        btn_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("author", "Guo lin");
                db.update("book", values, "name = ?", new String[]{"first line code"});
            }
        });

        btn_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.delete("book", "name=?", new String[]{"second line code"});
            }
        });

        btn_query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor cursor = db.query("book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "book name is " + name);
                        Log.d(TAG, "book author is " + author);
                        Log.d(TAG, "book pages is " + pages);
                        Log.d(TAG, "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
