package com.citywalker.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    EditText txt;
    private static final String TAG = "SecondActivity";

    @Override
    public void onBackPressed() {
        String msg = txt.getText().toString();

        if (msg==null||msg.length()==0)
        {
            Toast.makeText(this,"输入一些什么吧！",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        Log.d("TEXT", txt.getText().toString());
        intent.putExtra("return_data", txt.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, this.toString());
        Log.d(TAG, "Task id is " + getTaskId());

        txt = findViewById(R.id.txt_remark);
//        txt.setText("输入内容：");

        Intent intent = getIntent();
        String message = intent.getStringExtra("key");
        if (message != null && message.length() > 0) {
//        EditText text = findViewById(R.id.txt_remark);
//        text.setText(message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Log.d("TEXT", txt.getText().toString());
                intent.putExtra("return_data", txt.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.btn_start_first_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_start_third_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
//                startActivity(intent);

                ThirdActivity.actionStart(SecondActivity.this,"参数1","参数2");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
