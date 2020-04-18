package com.example.yangzhe.learn.mvp.view

import com.example.yangzhe.learn.mvp.presenter.BasePresenter

interface BaseView<P : BasePresenter> {
    /**
     * 给View层设置Presenter，这样View层不直接与Model层进行交互，而是通过中间的Presenter层进行交互
     * */
    fun setPresenter(presenter: P)
}