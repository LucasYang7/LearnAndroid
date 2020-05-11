package com.example.yangzhe.learn.mvvm.viewmodel

import androidx.databinding.ViewDataBinding

abstract class AbstractViewModel : BaseViewModel {
    var mViewDataBinding: ViewDataBinding? = null

    constructor(viewDataBinding: ViewDataBinding?) {
        mViewDataBinding = viewDataBinding
    }

    override fun onDestroy() {
        mViewDataBinding?.unbind()
    }
}
