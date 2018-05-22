package com.example.yangzhe.learntouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangzhe on 18-5-21.
 */
public class TouchEventViewGroup extends ViewGroup {
    private static final String TAG = "LearnTouchEvent";

    public TouchEventViewGroup(Context context) {
        super(context);
    }

    public TouchEventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int measuredWidth = 0, measuredHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            measuredWidth += child.getMeasuredWidth();
            measuredHeight += child.getMeasuredHeight();
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                final int childWidth = childView.getMeasuredWidth();
                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "TouchEventViewGroup dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "TouchEventViewGroup dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "TouchEventViewGroup dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "TouchEventViewGroup dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "TouchEventViewGroup onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "TouchEventViewGroup onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "TouchEventViewGroup onInterceptTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "TouchEventViewGroup onInterceptTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        // onInterceptTouchEvent默认返回false,即默认不拦截MotionEvent
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return super.onInterceptTouchEvent(ev);
        } else {
            // 强制拦截MotionEvent,此后ViewGroup不会再次调用onInterceptTouchEvent方法,
            // 而针对这次事件,子View会收到一个ACTION_CANCEL
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "TouchEventViewGroup onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "TouchEventViewGroup onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "TouchEventViewGroup onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "TouchEventViewGroup onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        // 这里的super.onTouchEvent(ev)调用的是View中的onTouchEvent方法,
        // 所以其也是根据View的clickable等属性来决定onTouchEvent方法的返回值
        return super.onTouchEvent(ev);
//        return true;   // 强制消费MotionEvent
    }
}
