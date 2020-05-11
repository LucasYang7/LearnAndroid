package com.example.yangzhe.learn.mvvm.model

import com.example.yangzhe.learn.mvvm.callback.Callback1

class SampleModel : BaseModel {
    private var mUserInfoMap: HashMap<String, UserInfo> = HashMap()

    init {
        initUserInfoList()
    }

    override fun onDestroy() {
        mUserInfoMap.clear()
    }

    fun getUserInfo(uid: String, callback: Callback1<UserInfo>) {
        // 模拟从网上下载数据或者从本地读取缓存
        val userInfo: UserInfo = mUserInfoMap[uid] ?: UserInfo("empty", 0)
        callback.onCallback(userInfo)
    }

    private fun initUserInfoList() {
        for (index in 1..100) {
            mUserInfoMap.put(index.toString(), UserInfo("Lucas$index", 29));
        }
    }

    data class UserInfo(var name: String, var age: Int)
}