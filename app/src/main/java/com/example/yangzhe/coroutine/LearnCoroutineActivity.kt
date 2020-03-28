package com.example.yangzhe.coroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yangzhe.learnactivity.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LearnCoroutineActivity : AppCompatActivity() {
    companion object{
        const val TAG = "LearnCoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_coroutine)
        testCoroutine()
    }

    fun testCoroutine() {
        GlobalScope.launch {
            delay(1000)
            Log.d(TAG, "coroutine world!")
        }
        Log.d(TAG, "hello")
    }
}
