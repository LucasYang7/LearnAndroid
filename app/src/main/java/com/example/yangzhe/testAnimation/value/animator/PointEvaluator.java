package com.example.yangzhe.testAnimation.value.animator;

import android.animation.TypeEvaluator;

/**
 * Created by yangzhe on 18-5-25.
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        float radius = startPoint.getRadius() + fraction * (endPoint.getRadius() - startPoint.getRadius());
        Point point = new Point(x, y, radius);
        return point;
    }
}
