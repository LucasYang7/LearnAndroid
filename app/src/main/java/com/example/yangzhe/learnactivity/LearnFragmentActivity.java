package com.example.yangzhe.learnactivity;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnfragment.FirstFragment;
import com.example.yangzhe.learnfragment.SecondFragment;

public class LearnFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_fragment);
        // add a fragment in java
        Button btnShowFragment1 = (Button)findViewById(R.id.buttonShowFragment1);
        btnShowFragment1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FirstFragment firstFragment = new FirstFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_in_activity_layout,
                        firstFragment).commit();
            }
        });

        Button btnShowFragment2 = (Button)findViewById(R.id.buttonShowFragment2);
        btnShowFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment secondFragment = new SecondFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_in_activity_layout,
                        secondFragment).commit();
            }
        });
    }
}
