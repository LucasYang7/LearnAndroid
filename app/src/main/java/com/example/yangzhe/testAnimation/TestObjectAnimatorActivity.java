package com.example.yangzhe.testAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yangzhe.learnactivity.R;

public class TestObjectAnimatorActivity extends AppCompatActivity {

    private Button mTestPropertyAnimation;
    private ImageView mAnimationImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        mTestPropertyAnimation = (Button) findViewById(R.id.btnTestPropertyAnimation);
        mAnimationImg = (ImageView) findViewById(R.id.iv_animation);
        final ObjectAnimator animatorX = ObjectAnimator
                .ofFloat(mAnimationImg, "translationX", 500, 0)
                .setDuration(1000);
        final ObjectAnimator animatorY = ObjectAnimator
                .ofFloat(mAnimationImg, "translationY", 500, 250)
                .setDuration(1000);
        final AnimatorSet set = new AnimatorSet();
//        set.playTogether(animatorX,animatorY);  // 两个动画同时执行
        set.playSequentially(animatorX, animatorY); // 两个动画依次执行
        mTestPropertyAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.start();
            }
        });
        mAnimationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestObjectAnimatorActivity.this, "点击了图片!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
