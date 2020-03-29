package com.example.yangzhe.coroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yangzhe.learnactivity.R
import kotlinx.coroutines.*

class LearnCoroutineActivity : AppCompatActivity() {
    companion object {
        const val TAG = "LearnCoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_coroutine)
        Log.d(TAG, "onCreate() start")
//        testCoroutine()
//        testRunBlocking()
        testCoroutineScope()
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

    private fun testCoroutineScope() = runBlocking {
        launch {  // 启动一个协程
            delay(200L)
            Log.d(TAG, "Task from runBlocking launch.")
        }

        coroutineScope {  // 创建一个协程作用域
            launch {  // 启动一个协程
                printCoroutineScopeLaunch()
            }
            delay(100L)
            Log.d(TAG, "Task from coroutineScope.")  // 因为在coroutineScope中，这一行会在内嵌的协程结束前输出
        }

        Log.d(TAG, "testCoroutineScope() is over.")  // 因为在runBlocking中，这一行会等上面两个协程都执行完成后才输出。
    }

    /**
     * 抽取出来的suspend方法
     * */
    private suspend fun printCoroutineScopeLaunch() {
        delay(500L)
        Log.d(TAG, "Task from coroutineScope launch, this is in suspend function.")
    }
}
