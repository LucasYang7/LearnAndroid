package com.example.yangzhe.custome.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangzhe on 17-9-14.
 */
public class CanvasView extends View {
    Paint mPaint;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();   // 由于在调用save()函数之前没有对canvas做任何操作，因此这里保存的是canvas的原始尺寸状态，也就是整个屏幕的大小
        canvas.clipRect(100, 100, 200, 200);   // 将canvas的范围限制为(100,100)到(200,200)之间
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 200, 300, mPaint); // 只会显式(100,100)到(200,200)之间的矩形区域
        canvas.restore();   // 撤销save()与restore()之间的clipRect操作，而且只撤销与Canvas相关的操作，不会对Paint造成影响
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 150, 150, mPaint);
        mPaint.setColor(Color.GREEN);
        // 因为前面的操作将canvas的范围限制为(100,100)到(200,200)之间，而下面的矩形范围是(0,0)到(50,50)之间
        // 如果没有调用canvas.restore()，是无法看到所绘制的绿色矩形区域的
        canvas.drawRect(0, 0, 50, 50, mPaint);
    }
}
