package com.citywalker.servicetest;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startServie = findViewById(R.id.start_service);
        Button stopServie = findViewById(R.id.stop_servie);
        Button bindService = findViewById(R.id.bind_servie);
        Button unbindService = findViewById(R.id.unbind_servie);
        Button startIntentService = findViewById(R.id.start_intent_servie);

        startServie.setOnClickListener(this);
        stopServie.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_servie:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_servie:
                Intent intent = new Intent(this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_servie:
                unbindService(connection);
                break;
            case R.id.start_intent_servie:
                Log.d("MainActivity", "Main ThreadID is " + Thread.currentThread().getId());
                Intent intent2 = new Intent(this, MyIntentService.class);
                startService(intent2);
                break;
            default:
        }
    }
}
