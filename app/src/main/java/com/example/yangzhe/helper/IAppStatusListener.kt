package com.example.yangzhe.helper

/**
 * 监听App前后台切换的回调
 * */
interface IAppStatusListener {
    fun onFront()
    fun onBack()
    fun onFirstStart()
}