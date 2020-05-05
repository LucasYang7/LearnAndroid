package com.example.yangzhe.learnactivity;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Choreographer;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;

import com.example.yangzhe.frame.callback.FPSFrameCallback;
import com.example.yangzhe.helper.AppFrontBackHelper;
import com.example.yangzhe.helper.IAppStatusListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by yangzhe on 16-6-16.
 */
public class LearnAndroidApplication extends Application {
    private static final String TAG = "LearnAndroidApplication";
    private AppFrontBackHelper mAppFrontBackHelper = new AppFrontBackHelper();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        Choreographer.getInstance().postFrameCallback(new FPSFrameCallback(System.nanoTime()));
        mAppFrontBackHelper.register(LearnAndroidApplication.this, new IAppStatusListener() {
            @Override
            public void onFront() {
                Log.d(TAG, "应用从后台切换到了前台");
            }

            @Override
            public void onBack() {
                Log.d(TAG, "应用从前台切换到了后台");
            }

            @Override
            public void onFirstStart() {
                Log.d(TAG, "应用首次启动...");
            }
        });
        Fresco.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mAppFrontBackHelper.unRegister(this);
        Log.d(TAG, "进程被终止运行了...");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void initImageLoader(Context context) {
        // ImageLoaderConfiguration is global for application,you should set it once.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();

        ImageLoader.getInstance().init(config.build());
    }
}
