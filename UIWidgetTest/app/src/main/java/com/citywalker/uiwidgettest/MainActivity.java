package com.citywalker.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.txt_remark);
        imageView = findViewById(R.id.img02);
        progressBar = findViewById(R.id.progress_bar);

        // method 1
//        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "You clicked a button", Toast.LENGTH_SHORT).show();
//            }
//        });

        // method 2 implements View.OnClickListener
        findViewById(R.id.btn_click).setOnClickListener(this);
        findViewById(R.id.btn_editText).setOnClickListener(this);
        findViewById(R.id.btn_imageView).setOnClickListener(this);
        findViewById(R.id.btn_progressbar).setOnClickListener(this);
        findViewById(R.id.btn_alert_dialog).setOnClickListener(this);
        findViewById(R.id.btn_progress_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                Toast.makeText(MainActivity.this, "You clicked a button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_editText:
                String content = editText.getText().toString();
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_imageView:
                imageView.setImageResource(R.drawable.img02);
                break;
            case R.id.btn_progressbar:
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                int value = progressBar.getProgress();
                value += 10;
                progressBar.setProgress(value);

// else {
//                    progressBar.setVisibility(View.GONE);
//                }
                break;
            case R.id.btn_alert_dialog:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is a dialog");
                dialog.setMessage("This is very important message");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"You clicked OK",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"You clicked Cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case R.id.btn_progress_dialog:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is a progress dialog");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                //progressDialog.dismiss(); // close dialog
                break;
            default:
        }
    }
}
