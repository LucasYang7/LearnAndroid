package com.example.yangzhe.custome.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

/**
 * Created by yangzhe on 17-6-25.
 */
public class CustomeView extends TextView {

    private int mTextSize;
    private int mTextColor;

    public CustomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomeView);
        mTextSize = a.getDimensionPixelSize(R.styleable.CustomeView_textSize,10);
        mTextColor = a.getColor(R.styleable.CustomeView_textColor,0);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(w,h);
        setMeasuredDimension(w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setTextSize(mTextSize);
        setTextColor(mTextColor);
    }
}
