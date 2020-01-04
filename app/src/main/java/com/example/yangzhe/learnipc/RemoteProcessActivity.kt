package com.example.yangzhe.learnipc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yangzhe.learnactivity.R
import kotlinx.android.synthetic.main.activity_remote_process.*

class RemoteProcessActivity : AppCompatActivity() {

    companion object {
        const val TAG = "RemoteProcessActivity"
        const val KEY_STRING_VALUE = "key_string_value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_process)
        val stringValue = intent.getStringExtra(KEY_STRING_VALUE)
        tv_string_value.text = stringValue
        Log.d(TAG, "stringValue is $stringValue")
    }
}
