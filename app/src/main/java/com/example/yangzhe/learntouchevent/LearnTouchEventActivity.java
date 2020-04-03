package com.example.yangzhe.learntouchevent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yangzhe.learnactivity.R;

public class LearnTouchEventActivity extends AppCompatActivity {

    private static final String TAG = "LearnTouchEvent";
    private TouchEventView mTouchEventView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_touch_event);
        mTouchEventView = (TouchEventView) findViewById(R.id.touch_event_view);
        // View的enabled属性并不会影响View的onTouchEvent回调的返回值,但是通过View的onTouchEvent源代码可以得知,
        // 如果View的enable属性设置为false,onTouchEvent方法会根据View的clickable,longClickable等属性值直接返回结果,
        // 而不会再去处理ACTION_DOWN,ACTION_MOVE,ACTION_UP,也会导致View无法响应onTouch,onClick等事件
        mTouchEventView.setEnabled(false);
        mTouchEventView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // onTouch方法可以在MotionEvent传递给目标View前提前拦截并且消费事件,
                // 如果onTouch方法返回true,就不会再调用onTouchEvent方法
                Log.d(TAG, "TouchEventView onTouch");
                return false;
            }
        });
        mTouchEventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onClick方法在onTouchEvent方法中的ACTION_UP事件中调用
                Log.d(TAG, "TouchEventView onClick");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LearnTouchEventActivity dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LearnTouchEventActivity dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LearnTouchEventActivity dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "LearnTouchEventActivity dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LearnTouchEventActivity onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LearnTouchEventActivity onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LearnTouchEventActivity onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "LearnTouchEventActivity onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }
}
