package com.citywalker.androidthreadtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/14.
 */

public class DownLoadTask extends AsyncTask<Void, Integer, Boolean> {
    private Context _context;

    public DownLoadTask(Context context) {
        _context = context;
    }

    private ProgressDialog progressDialog = new ProgressDialog(_context);

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            while (true) {
                int percent = 0;//doDownLoad();
                publishProgress(percent);
                if (percent >= 100) {
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        progressDialog.dismiss();
        if (b) {
            Toast.makeText(_context, "Download succeed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(_context, "Download failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setMessage("Downloaded " + values[0] + "%");
    }
}
