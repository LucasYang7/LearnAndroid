package com.example.yangzhe.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yangzhe.learnactivity.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

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
//        testCoroutineScope()
//        launchCoroutines()
        Log.d(TAG, "onCreate() end")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() start")
        Log.d(TAG, "cost time is ${getCostTime()}.")
        Log.d(TAG, "async cost time is ${getAsyncCostTime()}.")
        Log.d(TAG, "onResume() end")
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
        launch {
            // 启动一个协程
            delay(200L)
            Log.d(TAG, "Task from runBlocking launch.")
        }

        coroutineScope {
            // 创建一个协程作用域
            launch {
                // 启动一个协程
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

    /**
     * 开启100000个协程
     * */
    private fun launchCoroutines() {
        runBlocking {
            var index = 0
            // 协程是一种轻量级线程，开启很多个协程也不会造成内存溢出问题
            repeat(100_000) {
                launch {
                    delay(1000L)
                    Log.d(TAG, "index = ${index++}.")
                }
            }
        }
    }

    // 模拟一个耗时1秒的操作并且返回结果
    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 100
    }

    // 模拟一个耗时1秒的操作并且返回结果
    suspend fun doSomethingUsefulTwo(): Int {
        delay(2000L)
        return 200
    }

    // 获取协程执行的耗时
    fun getCostTime() = measureTimeMillis {
        runBlocking {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            Log.d(TAG, "result is $one + $two =  ${one + two}")
        }
    }

    // 获取协程异步执行的耗时
    fun getAsyncCostTime() = measureTimeMillis {
        runBlocking {
            // one和two可以同时执行
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            Log.d(TAG, "result is ${one.await()} + ${two.await()} =  ${one.await() + two.await()}")
        }
    }
}
