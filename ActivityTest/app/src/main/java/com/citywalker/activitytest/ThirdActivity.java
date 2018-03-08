package com.citywalker.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ThirdActivity extends BaseActivity {
    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Log.d(TAG, "Task id is " + getTaskId());

        Intent intent = getIntent();
        String p1 = intent.getStringExtra("p1");
        String p2 = intent.getStringExtra("p2");

        if (p1 != null && p1.length() > 0 && p2 != null && p2.length() > 0) {
            Toast.makeText(this, p1 + " " + p2, Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.btn_finish_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

    }

    public static void actionStart(Context ctx, String p1, String p2) {
        Intent intent = new Intent(ctx, ThirdActivity.class);
        intent.putExtra("p1", p1);
        intent.putExtra("p2", p2);

        ctx.startActivity(intent);
    }
}
