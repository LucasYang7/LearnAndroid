package com.example.yangzhe.learn.mvp.model

import com.example.yangzhe.learn.mvp.callback.Callback1

class SampleModel : BaseModel {
    private var mUserInfoMap: HashMap<String, UserInfo> = HashMap()

    init {
        initUserInfoList()
    }

    override fun onDestroy() {
        mUserInfoMap.clear()
    }

    fun getUserInfo(uid: String, callback: Callback1<UserInfo>) {
        val userInfo: UserInfo = mUserInfoMap[uid] ?: UserInfo("empty", 0)
        callback.onCallback(userInfo)
    }

    private fun initUserInfoList() {
        for (index in 1..100) {
            mUserInfoMap.put(index.toString(), UserInfo("Lucas$index", 29));
        }
    }

    data class UserInfo(val name: String, val age: Int)
}