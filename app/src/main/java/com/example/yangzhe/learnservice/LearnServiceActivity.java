package com.example.yangzhe.learnservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnactivity.R;

public class LearnServiceActivity extends AppCompatActivity {

    private String TAG = "LearnServiceActivity";

    private Button btnStartService;
    private Button btnStopService;
    private Button btnBindService;
    private Button btnUnbindService;
    private Button btnStartForegroundService;
    private Button btnStartIntentService;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            // 调用MyService.DownloadBinder中的回调方法
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
        setContentView(R.layout.activity_learn_service);
        btnStartService = (Button)findViewById(R.id.button_stop_service);
        btnStartService.setOnClickListener(onClickListener);
        btnStopService = (Button)findViewById(R.id.button_start_service);
        btnStopService.setOnClickListener(onClickListener);
        btnBindService = (Button)findViewById(R.id.button_bind_service);
        btnBindService.setOnClickListener(onClickListener);
        btnUnbindService = (Button)findViewById(R.id.button_unbind_service);
        btnUnbindService.setOnClickListener(onClickListener);
        btnStartForegroundService = (Button)findViewById(R.id.button_start_foreground_service);
        btnStartForegroundService.setOnClickListener(onClickListener);
        btnStartIntentService = (Button)findViewById(R.id.button_start_intent_service);
        btnStartIntentService.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_start_service:
                    Intent startIntent = new Intent(LearnServiceActivity.this,MyService.class);
                    startService(startIntent); // 启动服务 startService和stopService都是定义在Context类里面的
                    break;

                case R.id.button_stop_service:
                    Intent stopIntent = new Intent(LearnServiceActivity.this,MyService.class);
                    stopService(stopIntent);  // 停止服务
                    break;

                case R.id.button_bind_service:
                    Intent bindIntent = new Intent(LearnServiceActivity.this,MyService.class);
                    bindService(bindIntent,connection,BIND_AUTO_CREATE);   // 绑定服务
                    break;

                case R.id.button_unbind_service:
                    unbindService(connection);        // 解绑服务
                    break;

                case R.id.button_start_foreground_service:
                    Intent startForegroundIntent = new Intent(LearnServiceActivity.this
                            ,ForegroundService.class);
                    startService(startForegroundIntent);   // 开启前台服务
                    break;

                case R.id.button_start_intent_service:
                    Intent startIntentService = new Intent(LearnServiceActivity.this
                            ,MyIntentService.class);
                    startIntentService.putExtra("intentServiceString",
                            "This is from LearnServiceActivity...");
                    startService(startIntentService);
                    Log.e(TAG,"LearnServiceActivity Thread id is " + Thread.currentThread().getId());
                    break;

                default:
                    break;
            }
        }
    };
}
