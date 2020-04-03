package com.example.yangzhe.testAnimation.value.animator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnactivity.R;

public class TestValueAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_value_animator);
        final MyAnimView myAnimView = (MyAnimView) findViewById(R.id.my_anim_view);
        Button btnPlayValueAnimator = (Button) findViewById(R.id.btn_play_value_animator);
        btnPlayValueAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimView.startAnimation();
            }
        });
    }
}
