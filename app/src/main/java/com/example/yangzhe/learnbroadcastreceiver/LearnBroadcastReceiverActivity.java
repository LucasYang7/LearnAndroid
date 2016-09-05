package com.example.yangzhe.learnbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yangzhe.learnactivity.R;

public class LearnBroadcastReceiverActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private Button btnSendNormalBroadcast;
    private Button btnSendOrderedBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_broadcast_receiver);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter); // 动态注册广播接收器
        btnSendNormalBroadcast = (Button)findViewById(R.id.buttonSendNormalBroadscast);
        btnSendNormalBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yangzhe.intent.action.MY_BROADCAST");
                sendBroadcast(intent);        // 发送普通的广播
            }
        });
        btnSendOrderedBroadcast = (Button)findViewById(R.id.buttonSendOrderedBroadcast);
        btnSendOrderedBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.yangzhe.intent.action.MY_BROADCAST");
                sendOrderedBroadcast(intent,null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver); // 如果采用动态方式注册广播接收器，那么一定要取消注册操作
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //访问系统网络连接状态需要在AndroidManifest.xml中注册ACCESS_NETWORK_STATE权限
            //否则会出现　java.lang.SecurityException异常
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}
