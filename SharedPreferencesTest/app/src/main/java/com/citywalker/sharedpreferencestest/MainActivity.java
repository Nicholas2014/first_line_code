package com.citywalker.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button save;
    private Button restore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save_data);
        restore = findViewById(R.id.restore_data);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "citywalker");
                editor.putInt("age", 33);
                editor.putBoolean("male", true);
                editor.apply();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences("data", MODE_PRIVATE);
                String name = shared.getString("name", "");
                int age = shared.getInt("age", 0);
                Boolean male = shared.getBoolean("male", true);
                Toast.makeText(MainActivity.this, "name:" + name + " age:" + age + " male:" + male.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
