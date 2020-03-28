package com.example.yangzhe.coroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yangzhe.learnactivity.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LearnCoroutineActivity : AppCompatActivity() {
    companion object {
        const val TAG = "LearnCoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_coroutine)
        Log.d(TAG, "onCreate() start")
//        testCoroutine()
        testRunBlocking()
        Log.d(TAG, "onCreate() end")
    }

    private fun testCoroutine() {
        GlobalScope.launch {
            // 只会阻塞协程，不会阻塞线程
            delay(1000L)
            Log.d(TAG, "testCoroutine()!")
        }
    }

    private fun testRunBlocking() {
        // runBlocking会阻塞主线程
        return runBlocking {
            GlobalScope.launch {
                // 协程启动后延迟1000ms打印Log
                delay(1000L)
                Log.d(TAG, "testRunBlocking()")
            }
            // 主线程延迟2000ms
            delay(2000L)
        }
    }

}
