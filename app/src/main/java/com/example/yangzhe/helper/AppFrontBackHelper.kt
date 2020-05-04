package com.example.yangzhe.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * 监控应用切换前后台的辅助类
 * */
class AppFrontBackHelper {

    private var mAppStatusListener: IAppStatusListener? = null

    fun register(application: Application, appStatusListener: IAppStatusListener) {
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        mAppStatusListener = appStatusListener
    }

    fun unRegister(application: Application) {
        application.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    private var mActivityLifecycleCallbacks: Application.ActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        // 统计activity调用start回调的次数
        private var mActivityStartCount = 0;
        // 判断是否为首次启动进程
        private var isFirstStart = true;

        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
            mActivityStartCount++;
            // 统计次数从0到1说明从后台切换到了前台或者首次启动应用
            if (mActivityStartCount == 1) {
                if (!isFirstStart) {
                    mAppStatusListener?.onFront()
                } else {
                    mAppStatusListener?.onFirstStart()
                    isFirstStart = false;
                }
            }
        }

        override fun onActivityDestroyed(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
            mActivityStartCount--;
            // 因为Activity A启动Activity B时，会先调用B的onStart后，再去调用A的onStop方法
            // 所以正常情况下，mActivityStartCount不会变成0，当统计数据从1变到0说明是从前台切换到后台
            if (mActivityStartCount == 0) {
                mAppStatusListener?.onBack()
            }
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        }

    }
}