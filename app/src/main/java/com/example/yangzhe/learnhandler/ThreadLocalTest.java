package com.example.yangzhe.learnhandler;

import android.util.Log;

/**
 * Created by yangzhe on 19-11-2.
 */
public class ThreadLocalTest {
    private static final String TAG = "ThreadLocalTest";
    // ThreadLocal的数据以线程为作用域，不同的线程有不同的数据副本。
    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();

    public void printLog() {
        mBooleanThreadLocal.set(true);
        Log.d(TAG, "[Thread main]mBooleanThreadLocal is " + mBooleanThreadLocal.get());

        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                Log.d(TAG, "[Thread #1]mBooleanThreadLocal is " + mBooleanThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                Log.d(TAG, "[Thread #2]mBooleanThreadLocal is " + mBooleanThreadLocal.get());
            }
        }.start();
    }

}
