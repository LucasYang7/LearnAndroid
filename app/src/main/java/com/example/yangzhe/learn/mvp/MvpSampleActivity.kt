package com.example.yangzhe.learn.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yangzhe.learn.mvp.callback.Callback1
import com.example.yangzhe.learn.mvp.contract.SampleContract
import com.example.yangzhe.learn.mvp.model.SampleModel
import com.example.yangzhe.learnactivity.R
import kotlinx.android.synthetic.main.activity_mvp_sample.*

/***
 * Activity继承View接口，承担着View层的角色
 */
class MvpSampleActivity : AppCompatActivity(), SampleContract.View {

    var mPresenter: SampleContract.Companion.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp_sample)
        // MvpSampleActivity继承了View层，在这里给View层设置Presenter对象
        setPresenter(SampleContract.Companion.Presenter())
        btn_mvp_sample_user_info.setOnClickListener {
            // Presenter层接受来自View层的事件，然后将其传递给Model层
            mPresenter?.getUserInfo(edt_mvp_sample_uid.text.toString(), object : Callback1<SampleModel.UserInfo> {
                override fun onCallback(t: SampleModel.UserInfo) {
                    // Presenter层收到Model层的数据后，将该数据回传给View层进行展示
                    setDataToView(t)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

    override fun setDataToView(userInfo: SampleModel.UserInfo) {
        tv_mvp_sample_name.text = userInfo.name
        tv_mvp_sample_age.text = userInfo.age.toString()
    }

    override fun setPresenter(presenter: SampleContract.Companion.Presenter) {
        mPresenter = presenter
    }
}
