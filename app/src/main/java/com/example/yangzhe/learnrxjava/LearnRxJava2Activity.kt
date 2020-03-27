package com.example.yangzhe.learnrxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.yangzhe.learnactivity.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LearnRxJava2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_rx_java2)
        testRxJava2()
    }

    private fun testRxJava2(){
        Observable.just("1","2","3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(this@LearnRxJava2Activity,it,Toast.LENGTH_SHORT).show()
                    Log.d("LearnRxJava2Activity", it)
                },{

                },{

                })

    }
}
