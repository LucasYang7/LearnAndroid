package com.example.yangzhe.optimization.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

public class ViewStubActivity extends AppCompatActivity {

    private Button btnInflate;
    private ViewStub mViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        mViewStub = (ViewStub) findViewById(R.id.not_often_use);
        btnInflate = (Button) findViewById(R.id.btn_inflate);
        btnInflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateViewStub();
            }
        });
    }

    private void inflateViewStub() {
        // ViewStub的功能是实现延迟加载,可以通过setVisibility和inflate这两种方法来展开一个ViewStub
        // 但是inflate方法可以返回引用的布局，然后通过View.findViewById方法来找到相应的控件
        // ViewStub一旦被inflate后，就会被对应的布局layout所取代，所以inflate方法只能被调用一次
        View inflateView = mViewStub.inflate();
        TextView textView = (TextView) inflateView.findViewById(R.id.tv_in_view_stub);
        textView.setText("Hello ViewStub!");
    }
}
