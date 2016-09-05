package com.example.yangzhe.learnbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by yangzhe on 16-9-5.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"received in MyBroadcastReceiver",
                Toast.LENGTH_LONG).show();
        //abortBroadcast();          // 中止有序广播的传递，这样低权限的广播接收器将不再接收到这条广播
    }
}
