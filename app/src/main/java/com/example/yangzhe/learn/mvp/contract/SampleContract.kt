package com.example.yangzhe.learn.mvp.contract

import com.example.yangzhe.learn.mvp.callback.Callback1
import com.example.yangzhe.learn.mvp.model.SampleModel
import com.example.yangzhe.learn.mvp.presenter.BasePresenter
import com.example.yangzhe.learn.mvp.view.BaseView

/**
 * Google实现MVP使用的契约类，用于定义同一个界面的View和Presenter的具体实现
 * 这样做的好处是可以清晰地看到整个界面的逻辑，更方便管理
 * */
class SampleContract {
    companion object {
        /**
         * Present类接收View层的事件，然后将事件传递给Model
         * 等从Model读取完数据后，再将数据回传给View层进行展示
         * */
        class Presenter : BasePresenter {
            private val mSampleModel = SampleModel()

            override fun onDestroy() {
                mSampleModel.onDestroy()
            }

            // 从Model层读取数据，再通过Callback1接口回传给View层
            fun getUserInfo(uid: String, callback: Callback1<SampleModel.UserInfo>) {
                mSampleModel.getUserInfo(uid, callback)
            }
        }
    }

    /**
     * 因为Activity要继承View，而Kotlin不支持多重继承，所以View层一般设计成一个接口
     * 因为View一般是被动接收Presenter传来的数据然后进行展示，所以View层又被成为被动View(Passive View)
     * */
    interface View : BaseView<Presenter> {
        fun setDataToView(userInfo: SampleModel.UserInfo)
    }

}