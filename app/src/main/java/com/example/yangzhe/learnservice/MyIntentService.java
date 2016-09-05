package com.example.yangzhe.learnservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    // onHandleIntent方法运行于子线程中
    @Override
    protected void onHandleIntent(Intent intent) {
        String intentService = intent.getStringExtra("intentServiceString");
        Log.e(TAG,"MyIntentServie string " + intentService);
        Log.e(TAG,"MyIntentServie Thread id is " + Thread.currentThread().getId());
    }

    // IntentService在运行完毕后，会自动停止
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"MyIntentServie onDestroy executed");
    }
}
