package com.example.yangzhe.learnactivity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnfragment.AddFragment;
import com.example.yangzhe.learnfragment.FirstFragment;
import com.example.yangzhe.learnfragment.SecondFragment;

public class LearnFragmentActivity extends Activity {

    private static String TAG = "LearnFragmentActivityLifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"this is onCreate()!");
        setContentView(R.layout.activity_learn_fragment);

        // add a fragment in java
        Button btnAddFragment = (Button)findViewById(R.id.buttonAddFragment);
        btnAddFragment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AddFragment addFragment = new AddFragment();
                getFragmentManager().beginTransaction().add(R.id.dynamical_fragment_activity_layout,
                        addFragment).commit();
            }
        });

        Button btnShowFragment1 = (Button)findViewById(R.id.buttonShowFragment1);
        btnShowFragment1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FirstFragment firstFragment = new FirstFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.static_fragment_activity_layout,
                        firstFragment);
                fragmentTransaction.commit();
            }
        });

        Button btnShowFragment2 = (Button)findViewById(R.id.buttonShowFragment2);
        btnShowFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment secondFragment = new SecondFragment();
                getFragmentManager().beginTransaction().replace(R.id.dynamical_fragment_activity_layout,
                        secondFragment).commit();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"this is onStop()!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"this is onDestroy()!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"this is onStart()!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"this is onResume()!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"this is onPause()!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"this is onRestart()!");
    }
}
