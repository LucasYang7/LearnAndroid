package com.example.yangzhe.learnphotoview;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yangzhe on 16-7-15.
 */
public class HackyViewPager extends ViewPager {

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try{
            return super.onInterceptTouchEvent(ev);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }

    }
}
