package com.example.yangzhe.learnservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.yangzhe.learnactivity.R;

public class ForegroundService extends Service {

    private String TAG = "ForegroundService";

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent notificationIntent = new Intent(ForegroundService.this,LearnServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,
                notificationIntent,0);
        // 构造通知栏
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("This is title")
                .setContentText("This is content")
                .setContentIntent(pendingIntent)    // 当通知栏被点击时所执行的PendingIntent
                .setSmallIcon(R.drawable.weixin)
                .setWhen(System.currentTimeMillis());
        Notification notification = builder.getNotification();
        startForeground(1,notification);           // 启动前台服务 Service
        Log.e(TAG,"ForegroundService onCreate executed");
    }
}
