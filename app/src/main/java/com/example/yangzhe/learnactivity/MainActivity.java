package com.example.yangzhe.learnactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzhe.learnhandler.HandlerActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "LearnActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"this is onCreate()!");
        setContentView(R.layout.activity_main);
        Button btnDialog = (Button)findViewById(R.id.button_show_dialog);
        btnDialog.setOnClickListener(click);
        Button btnShowDialogActivity = (Button)findViewById(R.id.button_show_dialog_activity);
        btnShowDialogActivity.setOnClickListener(click);
        Button btnGotoHandlerActivity = (Button)findViewById(R.id.button_goto_handleractivity);
        btnGotoHandlerActivity.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_show_dialog:
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
                case R.id.button_show_dialog_activity:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,DialogActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_goto_handleractivity:
                    Intent intent2 = new Intent();
                    intent2.setClass(MainActivity.this,HandlerActivity.class);
                    startActivity(intent2);
                    break;
                default:
                    break;
            }
        }
    };

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
