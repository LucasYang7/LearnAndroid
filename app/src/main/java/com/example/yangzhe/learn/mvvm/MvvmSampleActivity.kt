package com.example.yangzhe.learn.mvvm

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.yangzhe.learn.mvvm.model.ObserableUser
import com.example.yangzhe.learn.mvvm.viewmodel.ProfileLiveDataViewModel
import com.example.yangzhe.learnactivity.R
import com.example.yangzhe.learnactivity.databinding.ActivityMvvmSampleBinding
import kotlinx.android.synthetic.main.activity_mvvm_sample.*

/**
 * MVVM中的View层对应Activity和Fragment，也就是xml布局
 * 用于展示数据给用户和处理用户的输入，
 * 然后驱动ViewModel从Model中获取数据
 * */
class MvvmSampleActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MvvmSampleActivity"
    }

    private val obserableUser = ObserableUser(ObservableField("Lucas"),
            ObservableField("Yang"),
            ObservableInt(0))

    private var mViewModel: ProfileLiveDataViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModel只能在onCreate()进行初始化
        mViewModel = ViewModelProviders.of(this@MvvmSampleActivity).get(ProfileLiveDataViewModel::class.java)
        // 初始化DataBinding,ActivityMvvmSampleBinding类由系统DataBinding生成，默认与xml文件名字保持一致。
        val dataBinding: ActivityMvvmSampleBinding? = DataBindingUtil.setContentView<ActivityMvvmSampleBinding>(
                this@MvvmSampleActivity, R.layout.activity_mvvm_sample)
        // 保证dataBinding可以感知到MvvmSampleActivity的生命周期，保证数据在可见性才更新，不可见时不会更新数据
        dataBinding?.lifecycleOwner = this@MvvmSampleActivity
        dataBinding?.title = "This title is from MvvmSampleActivity onCreate()."
        dataBinding?.user = obserableUser
        dataBinding?.profileViewModel = mViewModel

        // 创建Observer监听LiveData发生的数据变化
        val ageObserver: Observer<Integer> = object : Observer<Integer> {
            override fun onChanged(newAge: Integer?) {
                tv_live_data_age.text = "LiveData age is $newAge"
                Log.d(TAG, "LiveData age is $newAge.");
            }
        }

        // liveDataAge收到更新数据后，实时刷新界面
        mViewModel?.liveDataAge?.observe(this@MvvmSampleActivity, ageObserver)
    }

    /**
     * 该方法由 btn_add_age onClick 属性调用
     */
    fun onClickAddAge(view: View) {
        // 每点击一次，年龄加1s
        obserableUser.age.set(obserableUser.age.get() + 1)
        // 每点击一次，更新ViewModel中的LiveData值
        mViewModel?.liveDataAge?.value = Integer(obserableUser.age.get())
    }
}
