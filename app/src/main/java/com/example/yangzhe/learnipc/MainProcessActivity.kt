package com.example.yangzhe.learnipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.example.yangzhe.learnactivity.R
import com.example.yangzhe.learnipc.aidl.BookMangerService
import kotlinx.android.synthetic.main.activity_main_process.*

class MainProcessActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainProcessActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_process)
        tv_goto_remote_process_activity.setOnClickListener {
            // Intent适用于四大组件之间的多进程通信
            val intent = Intent(this@MainProcessActivity, RemoteProcessActivity::class.java)
            intent.putExtra(RemoteProcessActivity.KEY_STRING_VALUE, "Hello,I am from MainProcessActivity!")
            startActivity(intent)
        }

        tv_bind_remote_service.setOnClickListener {
            // 绑定远程服务
            val intent = Intent(this@MainProcessActivity, BookMangerService::class.java)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        unbindService(mServiceConnection)
        super.onDestroy()
    }

    private var mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 获取远程进程中的service对象，转换成IBookManager接口对象，然后客户端可以通过调用IBookManager中的方法与服务端进行交互
            var bookManager: IBookManager? = IBookManager.Stub.asInterface(service)
            try {
                // 从远程Service获取图书列表信息
                var bookList = bookManager?.bookList
                bookList?.let { list ->
                    for (book in list) {
                        Log.d(TAG, "book id is ${book.bookId},book name is ${book.bookName}. " +
                                "Current Process is ${android.os.Process.myPid()}")
                    }
                }
                // 调用远程接口添加图书信息
                bookManager?.addBook(Book(3, "Kotlin"))
                // 添加完图书信息后，再次从远程Service获取图书列表信息
                bookList = bookManager?.bookList
                bookList?.let { list ->
                    for (book in list) {
                        Log.d(TAG, "book id is ${book.bookId},book name is ${book.bookName}." +
                                "Current Process is ${android.os.Process.myPid()}")
                    }
                }
            } catch (e: RemoteException) {
                Log.e(TAG, "e is $e.")
            }
        }

    }
}
