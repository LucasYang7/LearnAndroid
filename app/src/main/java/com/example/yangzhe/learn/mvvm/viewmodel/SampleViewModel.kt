package com.example.yangzhe.learn.mvvm.viewmodel

import androidx.databinding.ViewDataBinding
import com.example.yangzhe.learn.mvvm.callback.Callback1
import com.example.yangzhe.learn.mvvm.model.SampleModel
import com.example.yangzhe.learn.mvvm.viewmodel.AbstractViewModel

class SampleViewModel(viewDataBinding: ViewDataBinding?) : AbstractViewModel(viewDataBinding) {
    // 模拟从网上下载数据或者从本地读取缓存数据
    fun getUserInfo(uid: String, callback: Callback1<SampleModel.UserInfo>) {
        var userInfo: SampleModel.UserInfo = SampleModel.UserInfo("OYZ", 29)
        callback.onCallback(userInfo)
    }
}
