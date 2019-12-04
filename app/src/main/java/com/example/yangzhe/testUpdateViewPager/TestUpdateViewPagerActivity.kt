package com.example.yangzhe.testUpdateViewPager

import android.app.Activity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yangzhe.learnactivity.R

class TestUpdateViewPagerActivity : Activity() {
    var mViewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_test_update_viewpager)
        mViewPager = findViewById(R.id.test_view_pager) as ViewPager?
        mViewPager?.adapter = SimpleViewPagerAdapter()
    }

    /**
     * ViewPager适配器
     * */
    class SimpleViewPagerAdapter : PagerAdapter() {
        private val itemList = ArrayList<String>().apply {
            add("0")
            add("1")
            add("2")
            add("3")
            add("4")
            add("5")
            add("6")
            add("7")
            add("8")
            add("9")
        }

        override fun isViewFromObject(view: View?, item: Any?): Boolean {
            return view == item as? View
        }

        override fun getCount(): Int {
            return itemList.size
        }

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            val itemView = LayoutInflater.from(container?.context).inflate(R.layout.item_view_pager, container, false)
            val itemText: TextView? = itemView.findViewById(R.id.tv_item_view_pager) as? TextView
            itemText?.text = itemList[position]
            container?.addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup?, position: Int, item: Any?) {
            container?.removeView(item as? View)
        }
    }
}