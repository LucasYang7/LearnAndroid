package com.example.yangzhe.learnbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.yangzhe.learnservice.MyService;

/**
 * Created by yangzhe on 16-9-5.
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    // 注意：onReceive方法不能开线程，因此不能执行耗时的操作
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Boot Complete!!!",Toast.LENGTH_LONG).show();
        Intent startIntent = new Intent(context,MyService.class);
        context.startService(startIntent); // 启动服务 startService和stopService都是定义在Context类里面的
    }
}
