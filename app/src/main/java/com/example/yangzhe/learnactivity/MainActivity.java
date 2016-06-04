package com.example.yangzhe.learnactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "LearnActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"this is onCreate()!");
        setContentView(R.layout.activity_main);
        Button btnDialog = (Button)findViewById(R.id.button_show_dialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("dialog").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog =  builder.create();
                alertDialog.show();
            }
        });

        Button btnShowDialogActivity = (Button)findViewById(R.id.button_show_dialog_activity);
        btnShowDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,DialogActivity.class);
                startActivity(intent);
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
