package com.example.yangzhe.learn.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.yangzhe.learnactivity.R
import com.example.yangzhe.learnactivity.databinding.ActivityMvvmSampleBinding

class MvvmSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化DataBinding,ActivityMvvmSampleBinding类由系统DataBinding生成，默认与xml文件名字保持一致。
        val dataBinding: ActivityMvvmSampleBinding? = DataBindingUtil.setContentView<ActivityMvvmSampleBinding>(
                this@MvvmSampleActivity, R.layout.activity_mvvm_sample)
        dataBinding?.lifecycleOwner = this@MvvmSampleActivity
        dataBinding?.title = "This title is from MvvmSampleActivity onCreate()."
    }
}
