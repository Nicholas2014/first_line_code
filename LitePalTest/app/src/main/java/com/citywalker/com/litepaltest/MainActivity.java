package com.citywalker.com.litepaltest;

import android.database.Cursor;
import android.service.autofill.Dataset;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createdb = findViewById(R.id.create_database);
        createdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
            }
        });

        Button add = findViewById(R.id.btn_add_data);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("First line code");
                book.setAuthor("Guolin");
                book.setPages(470);
                book.setPrice(45.5);
                book.setPress("ji xie chu ban she ");
                book.save();
            }
        });

        Button update = findViewById(R.id.btn_update_data);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Book book = new Book();
                book.setName("second line code");
                book.setAuthor("Guo lin");
                book.setPages(470);
                book.setPrice(45.5);
                book.setPress("ji xie chu ban she ");
                book.save();
                book.setPrice(30);
                book.save();*/

                // update method 2
               /* Book b = new Book();
                b.setAuthor("guo shen");
                b.updateAll("name=? and author=?","second line code","Guo lin");*/

                // 更新成默认值
                Book b2 = new Book();
                b2.setToDefault("pages");
                b2.updateAll();
            }
        });

        Button del = findViewById(R.id.btn_delete_data);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class, "price<?", "40");
            }
        });

        Button query = findViewById(R.id.btn_query_data);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = DataSupport.findAll(Book.class);
                // 复杂查询
                List<Book> bookList = DataSupport.select("name", "author", "pages")
                        .where("pages>?", "400")
                        .order("pages")
                        .limit(10)
                        .offset(10)
                        .find(Book.class);
                // 原生查询
                Cursor c = DataSupport.findBySQL("select * from book where pages>? and price<?", "400", "20");

                for (Book b : books) {
                    Log.d(TAG, "book name is " + b.getName());
                    Log.d(TAG, "book author is " + b.getAuthor());
                    Log.d(TAG, "book pages is " + b.getPages());
                    Log.d(TAG, "book price is " + b.getPrice());
                    Log.d(TAG, "book press is " + b.getPress());
                }
            }
        });
    }
}
