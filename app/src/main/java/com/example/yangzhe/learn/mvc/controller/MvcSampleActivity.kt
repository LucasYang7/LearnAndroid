package com.example.yangzhe.learn.mvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yangzhe.learn.mvc.callback.Callback1
import com.example.yangzhe.learn.mvc.model.SampleModel
import com.example.yangzhe.learnactivity.R
import kotlinx.android.synthetic.main.activity_mvc_sample.*

// MVC模式中Activity既担任了Controller角色，又担任了View角色
class MvcSampleActivity : AppCompatActivity() {
    // Model用于数据存储
    var mSampleModel: SampleModel = SampleModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc_sample)
        // View接受用户事件传递给Controller
        btn_mvc_sample_user_info.setOnClickListener {
            getUserInfo(edt_mvc_sample_uid.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSampleModel.onDestroy()
    }

    private fun getUserInfo(uid: String) {
        // Controller把事件传递给Model
        mSampleModel.getUserInfo(uid, object : Callback1<SampleModel.UserInfo> {
            // Model获取到数据后再传递给View
            override fun onCallback(t: SampleModel.UserInfo) {
                setDataToView(t)
            }
        })
    }

    fun setDataToView(userInfo: SampleModel.UserInfo) {
        tv_mvc_sample_name.text = userInfo.name
        tv_mvc_sample_age.text = userInfo.age.toString()
    }
}
