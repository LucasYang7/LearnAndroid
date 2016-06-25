package com.example.yangzhe.learnjson;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.support.Constants;
import com.example.yangzhe.support.HttpOperation;

import java.util.ArrayList;

public class LearnJsonActivity extends AppCompatActivity {
    public final String TAG = "LearnJsonActivity";
    private static TextView textViewShowPictureJson;
    private Button btnGotoShowMeinvActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_json);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewShowPictureJson = (TextView)findViewById(R.id.textViewShowPictureJson);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabLearnJson);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String httpUrl = Constants.ApiBaiduMeinvHttpConstants.httpUrl;
                        String httpArg = Constants.ApiBaiduMeinvHttpConstants.httpArg;
                        int numberOfpicture = Constants.ApiBaiduMeinvHttpConstants.numberOfPicture;
                        String jsonResult = HttpOperation.request(httpUrl,httpArg,numberOfpicture);
                        if(jsonResult == null){
                            jsonResult = Constants.ApiBaiduMeinvHttpConstants.failToGetJson;
                            Message msg = Message.obtain(handler);
                            msg.what = 0;
                            msg.obj = jsonResult;
                            msg.sendToTarget();
                        }else {
                            Message msg = Message.obtain(handler);
                            msg.what = 1;
                            msg.obj = jsonResult;
                            msg.sendToTarget();
                        }
                        //Log.e(TAG,jsonResult);
                    }
                }).start();

            }
        });

        btnGotoShowMeinvActivity = (Button)findViewById(R.id.buttonGotoShowMeinvActivity);
        btnGotoShowMeinvActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // goto ShowMeinvActivity
                Intent intent = new Intent(LearnJsonActivity.this,ShowMeinvActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * change Handler to WeakReference or change to AsyncTask
     * */
    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    textViewShowPictureJson.setText(msg.obj.toString());
                    break;

                case 1:
                    String json = msg.obj.toString();
                    Log.e(TAG,json);
                    int numberOfPicture = Constants.ApiBaiduMeinvHttpConstants.numberOfPicture;
                    ArrayList<String> picUrlList = JsonParser.getPicUrlFromJson(json,numberOfPicture);
                    StringBuilder stringBuilder = new StringBuilder();
                    for(String picUrl:picUrlList){
                        Log.e(TAG,picUrl);
                        stringBuilder = stringBuilder.append(picUrl + "\n");
                    }
                    textViewShowPictureJson.setText(stringBuilder.toString());
                    break;

                default:
                    break;
            }
        }
    };

}
