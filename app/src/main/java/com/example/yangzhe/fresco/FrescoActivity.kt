package com.example.yangzhe.fresco

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yangzhe.learnactivity.R
import com.facebook.drawee.view.SimpleDraweeView

class FrescoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)
        var draweeView = findViewById<SimpleDraweeView>(R.id.fresco_img)
        draweeView.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588707722497&di=9c8b1696ae112a922f6400bc4a9d11b3&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn13%2F694%2Fw480h214%2F20180504%2F5d61-fzyqqiq8046825.gif"))
    }
}
