package com.example.yangzhe.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private String TAG = "MyService";

    private DownloadBinder mDownloadBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG,"onBind");
        return mDownloadBinder;
    }

    // 创建服务时调用该方法
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate");
    }

    // 启动服务时调用该方法
    // 如果在启动服务时，发现服务还未被创建，那么要先调用onCreate()方法创建服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    // 销毁服务时调用该方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.e(TAG,"startDownload");
        }

        public int getProgress(){
            Log.e(TAG,"getProgress");
            return 0;
        }
    }
}
