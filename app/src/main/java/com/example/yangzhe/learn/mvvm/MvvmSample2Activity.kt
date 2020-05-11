package com.example.yangzhe.learn.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.yangzhe.learn.mvvm.callback.Callback1
import com.example.yangzhe.learn.mvvm.model.SampleModel
import com.example.yangzhe.learn.mvvm.viewmodel.SampleViewModel
import com.example.yangzhe.learnactivity.R
import com.example.yangzhe.learnactivity.databinding.ActivityMvvmSample2Binding
import kotlinx.android.synthetic.main.activity_mvvm_sample2.*

/**
 * MVVM中的View层对应Activity和Fragment，也就是xml布局
 * 用于展示数据给用户和处理用户的输入，
 * 然后驱动ViewModel从Model中获取数据
 * */
class MvvmSample2Activity : AppCompatActivity() {

    private var mViewModel: SampleViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化DataBinding,ActivityMvvmSampleBinding类由系统DataBinding生成，默认与xml文件名字保持一致。
        val dataBinding: ActivityMvvmSample2Binding? = DataBindingUtil.setContentView<ActivityMvvmSample2Binding>(
                this@MvvmSample2Activity, R.layout.activity_mvvm_sample2)
        mViewModel = SampleViewModel(dataBinding)
        btn_mvvm_get_user_info.setOnClickListener {
            setDataToView()
        }
    }

    fun setDataToView() {
        mViewModel?.getUserInfo("uid", object : Callback1<SampleModel.UserInfo> {
            override fun onCallback(userInfo: SampleModel.UserInfo) {
                (mViewModel?.mViewDataBinding as? ActivityMvvmSample2Binding)?.userInfo = userInfo
            }
        })
    }
}
