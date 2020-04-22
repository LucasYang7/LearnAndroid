package com.example.yangzhe.learn.mvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.yangzhe.learn.mvvm.model.ObserableUser
import com.example.yangzhe.learnactivity.R
import com.example.yangzhe.learnactivity.databinding.ActivityMvvmSampleBinding

class MvvmSampleActivity : AppCompatActivity() {

    private val obserableUser = ObserableUser(ObservableField("Lucas"),
            ObservableField("Yang"),
            ObservableInt(0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化DataBinding,ActivityMvvmSampleBinding类由系统DataBinding生成，默认与xml文件名字保持一致。
        val dataBinding: ActivityMvvmSampleBinding? = DataBindingUtil.setContentView<ActivityMvvmSampleBinding>(
                this@MvvmSampleActivity, R.layout.activity_mvvm_sample)
        // 保证dataBinding可以感知到MvvmSampleActivity的生命周期，保证数据在可见性才更新，不可见时不会更新数据
        dataBinding?.lifecycleOwner = this@MvvmSampleActivity
        dataBinding?.title = "This title is from MvvmSampleActivity onCreate()."
        dataBinding?.user = obserableUser
    }

    /**
     * 该方法由 btn_add_age onClick 属性调用
     */
    fun onClickAddAge(view: View) {
        // 每点击一次，年龄加1s
        obserableUser.age.set(obserableUser.age.get() + 1)
    }
}
