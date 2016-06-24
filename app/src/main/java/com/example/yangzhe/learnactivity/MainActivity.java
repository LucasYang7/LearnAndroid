package com.example.yangzhe.learnactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnhandler.HanderMessageActivity;
import com.example.yangzhe.learnhandler.HandlerActivity;
import com.example.yangzhe.learnjson.LearnJsonActivity;
import com.example.yangzhe.learnlayout.StaggeredGridLayoutActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivityLifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"this is onCreate()!");
        setContentView(R.layout.activity_main);
        Button btnDialog = (Button)findViewById(R.id.buttonShowDialog);
        btnDialog.setOnClickListener(click);
        Button btnShowDialogActivity = (Button)findViewById(R.id.buttonShowDialogActivity);
        btnShowDialogActivity.setOnClickListener(click);
        Button btnGotoHandlerActivity = (Button)findViewById(R.id.buttonGotoHandlerActivity);
        btnGotoHandlerActivity.setOnClickListener(click);
        Button btnGotoHandlerMessageActivity = (Button)findViewById(R.id.buttonGotoHandlerMessageActivity);
        btnGotoHandlerMessageActivity.setOnClickListener(click);
        Button btnGotoFragmentActivity = (Button)findViewById(R.id.buttonGotoFragmentActivity);
        btnGotoFragmentActivity.setOnClickListener(click);
        Button btnGotoImageLoaderActivity = (Button)findViewById(R.id.buttonGotoImageLoaderActivity);
        btnGotoImageLoaderActivity.setOnClickListener(click);
        Button btnGotoStaggeredGridActivity = (Button)findViewById(R.id.buttonGotoStaggeredGridActivity);
        btnGotoStaggeredGridActivity.setOnClickListener(click);
        Button btnGotoLearnJsonActivity = (Button)findViewById(R.id.buttonGotoLearnJsonActivity);
        btnGotoLearnJsonActivity.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.buttonShowDialog:
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("dialog").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog =  builder.create();
                    alertDialog.show();
                    break;

                case R.id.buttonShowDialogActivity:
                    gotoOtherActivity(DialogActivity.class);
                    break;

                case R.id.buttonGotoHandlerActivity:
                    gotoOtherActivity(HandlerActivity.class);
                    break;

                case R.id.buttonGotoHandlerMessageActivity:
                    gotoOtherActivity(HanderMessageActivity.class);
                    break;

                case R.id.buttonGotoFragmentActivity:
                    gotoOtherActivity(LearnFragmentActivity.class);
                    break;

                case R.id.buttonGotoImageLoaderActivity:
                    gotoOtherActivity(ImageLoaderActivity.class);
                    break;

                case R.id.buttonGotoStaggeredGridActivity:
                    gotoOtherActivity(StaggeredGridLayoutActivity.class);
                    break;

                case R.id.buttonGotoLearnJsonActivity:
                    gotoOtherActivity(LearnJsonActivity.class);
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * goto other activity
     * */
    private void gotoOtherActivity(Class clazz){
        startActivity(new Intent(MainActivity.this,clazz));
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
