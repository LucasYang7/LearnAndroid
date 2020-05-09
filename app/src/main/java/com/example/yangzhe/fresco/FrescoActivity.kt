package com.example.yangzhe.fresco

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.yangzhe.learnactivity.R
import com.facebook.drawee.view.SimpleDraweeView

class FrescoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)
        var draweeView = findViewById<SimpleDraweeView>(R.id.fresco_img)
        draweeView.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588707722497&di=9c8b1696ae112a922f6400bc4a9d11b3&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn13%2F694%2Fw480h214%2F20180504%2F5d61-fzyqqiq8046825.gif"))
        draweeView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // 耗时操作本身不会造成ANR，如果在执行了耗时操作后，又进行了触摸屏幕和按下按键的操作，
                // 系统没有在规定时间内响应触摸屏幕和按键的操作才会造成ANR。
                Thread.sleep(20000)
            }
        })
    }
}
