package com.example.yangzhe.testAnimation.value.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangzhe on 18-5-25.
 */
public class MyAnimView extends View {
    public static final float RADIUS = 50f;
    private Point mCurrentPoint;
    private Paint mPaint;

    public MyAnimView(Context context) {
        super(context);
    }

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrentPoint == null) {
            mCurrentPoint = new Point(0, 0, 0);
        }
        drawCircle(canvas);
    }

    public void startAnimation() {
        // 动画的起始坐标是MyAnimView的左上角
        Point startPoint = new Point(0, 0, 0);
        // 动画的结束坐标是MyAnimView的右下角
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS, RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();  // 获取当前动画的属性值
                invalidate();   // 调用invalidate后会重新调用onDraw()函数
            }
        });
        anim.setDuration(5000);
        anim.start();
    }

    private void drawCircle(Canvas canvas) {
        float x = mCurrentPoint.getX();
        float y = mCurrentPoint.getY();
        float radius = mCurrentPoint.getRadius();
        canvas.drawCircle(x, y, radius, mPaint);
    }
}
