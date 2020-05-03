package com.example.yangzhe.frame.callback

import android.os.Build
import android.util.Log
import android.view.Choreographer
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
class FPSFrameCallback : Choreographer.FrameCallback {

    companion object {
        const val TAG = "FPSFrameCallback"
        const val SKIP_FRAMES = 5
        const val FRAME_INTERVAL_NANOS = (1000000000 / 60.0).toLong()  // 处理一帧的时间间隔
    }

    constructor(lastFrameTimeNanos: Long) {
        mLastFrameTimeNanos = lastFrameTimeNanos
    }

    private var mLastFrameTimeNanos = 0L


    override fun doFrame(frameTimeNanos: Long) {
        // 初始化时间
        if (mLastFrameTimeNanos == 0L) {
            mLastFrameTimeNanos = frameTimeNanos
        }

        val jitterNanos = frameTimeNanos - mLastFrameTimeNanos
        if (jitterNanos >= FRAME_INTERVAL_NANOS) {
            val skippdFrames = jitterNanos / FRAME_INTERVAL_NANOS
            if (skippdFrames > SKIP_FRAMES) {
                Log.d(TAG, "Skipped $skippdFrames frames! The application may be doing too much work on its main thread.");
            }
        }
        mLastFrameTimeNanos = frameTimeNanos
        //注册下一帧回调
        Choreographer.getInstance().postFrameCallback(this)
    }
}