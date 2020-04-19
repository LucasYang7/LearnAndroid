package com.example.yangzhe.learnipc.aidl

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.yangzhe.learnipc.Book
import com.example.yangzhe.learnipc.IBookManager
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 服务端Service
 * */
class BookMangerService : Service() {

    companion object {
        const val TAG = "BMS"
    }

    var mBookList = CopyOnWriteArrayList<Book>()

    private var mBinder: Binder = object : IBookManager.Stub() {
        override fun addBook(book: Book?) {
            mBookList.add(book)
            Log.d(TAG, "BookMangerService addBook(). Current Process is ${android.os.Process.myPid()}")
        }

        override fun getBookList(): MutableList<Book> {
            Log.d(TAG, "BookMangerService getBookList(). Current Process is ${android.os.Process.myPid()}")
            return mBookList
        }
    }

    override fun onCreate() {
        super.onCreate()
        // 服务端Service往列表里面添加图书信息
        mBookList.add(Book(1, "Android"))
        mBookList.add(Book(2, "Java"))
        Log.d(TAG, "BookMangerService onCrate(). Current Process is ${android.os.Process.myPid()}")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "BookMangerService onBind(). Current Process is ${android.os.Process.myPid()}")
        // 返回Binder对象给客户端
        return mBinder
    }
}
