package com.example.yangzhe.learnipc

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.yangzhe.learnactivity.R
import kotlinx.android.synthetic.main.activity_main_process.*

class MainProcessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_process)
        tv_goto_remote_process_activity.setOnClickListener {
            // Intent适用于四大组件之间的多进程通信
            val intent = Intent(this@MainProcessActivity, RemoteProcessActivity::class.java)
            intent.putExtra(RemoteProcessActivity.KEY_STRING_VALUE, "Hello,I am from MainProcessActivity!")
            startActivity(intent)
        }
    }
}
