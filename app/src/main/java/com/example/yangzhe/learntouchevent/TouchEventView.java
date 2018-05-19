package com.example.yangzhe.learntouchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yangzhe on 18-5-19.
 */
public class TouchEventView extends View {

    private static final String TAG = "LearnTouchEvent";
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TouchEventView(Context context) {
        super(context);
    }

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth(), getHeight()) / 2, mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "TouchEventView dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "TouchEventView dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "TouchEventView dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "TouchEventView dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "TouchEventView onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "TouchEventView onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "TouchEventView onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "TouchEventView onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        // 因为View类的CLICKABLE和LONG_CLICKABLE默认值同时为false,所以View类的onTouchEvent的默认返回值也是false
        // 但是当TouchEventView的CLICKABLE或LONG_CLICKABLE属性为true时,其onTouchEvent的返回值会变为true
        return super.onTouchEvent(event);
    }
}
