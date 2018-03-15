package com.citywalker.servicebestpractice;

/**
 * Created by Administrator on 2018/3/15.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
