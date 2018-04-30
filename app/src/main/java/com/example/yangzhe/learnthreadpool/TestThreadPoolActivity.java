package com.example.yangzhe.learnthreadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnactivity.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mFixedThreadPoolBtn;
    private Button mCachedThreadPoolBtn;
    private Button mSheduledThreadPoolBtn;
    private Button mShutdownScheduledThreadPoolBtn;
    private Button mSingleThreadPoolBtn;
    private ScheduledExecutorService scheduledThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread_pool);
        mFixedThreadPoolBtn = (Button) findViewById(R.id.btn_fixed_thread_pool);
        mCachedThreadPoolBtn = (Button) findViewById(R.id.btn_cached_thread_pool);
        mSheduledThreadPoolBtn = (Button) findViewById(R.id.btn_scheduled_thread_pool);
        mShutdownScheduledThreadPoolBtn = (Button) findViewById(R.id.btn_shutdown_scheduledpool);
        mSingleThreadPoolBtn = (Button) findViewById(R.id.btn_single_thread_pool);
        mFixedThreadPoolBtn.setOnClickListener(this);
        mCachedThreadPoolBtn.setOnClickListener(this);
        mSheduledThreadPoolBtn.setOnClickListener(this);
        mSingleThreadPoolBtn.setOnClickListener(this);
        mShutdownScheduledThreadPoolBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fixed_thread_pool:
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
                if (fixedThreadPool instanceof ThreadPoolExecutor) {
                    ((ThreadPoolExecutor) fixedThreadPool).setKeepAliveTime(50, TimeUnit.MILLISECONDS);
                    // 核心线程超过预定时间后也可以被回收，在调用allowCoreThreadTimeOut之前需要先设置闲置时间
                    ((ThreadPoolExecutor) fixedThreadPool).allowCoreThreadTimeOut(true);
                }
                for (int i = 1; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fixedThreadPool.execute(command);
                }
                break;
            case R.id.btn_cached_thread_pool:
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                for (int i = 1; i <= 10; i++) {
                    cachedThreadPool.execute(command);
                }
                break;
            case R.id.btn_scheduled_thread_pool:
                scheduledThreadPool = Executors.newScheduledThreadPool(5);
                for (int i = 1; i <= 10; i++) {
                    scheduledThreadPool.scheduleAtFixedRate(command, 1000, 5000, TimeUnit.MILLISECONDS);
                }
                break;
            case R.id.btn_shutdown_scheduledpool:
                scheduledThreadPool.shutdown();
                break;
            case R.id.btn_single_thread_pool:
                ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
                for (int i = 1; i <= 10; i++) {
                    singleThreadPool.execute(command);
                }
                break;
            default:
                break;
        }
    }

    Runnable command = new Runnable() {
        @Override
        public void run() {
            Log.d("TestThreadPoolActivity", Thread.currentThread() + " command execute!");
        }
    };
}
