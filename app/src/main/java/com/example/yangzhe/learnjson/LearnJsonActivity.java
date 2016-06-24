package com.example.yangzhe.learnjson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.support.Constants;
import com.example.yangzhe.support.HttpOperation;

import java.util.ArrayList;

public class LearnJsonActivity extends AppCompatActivity {
    public final String TAG = "LearnJsonActivity";
    private static TextView textViewShowPictureJson;
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
                        Message msg = Message.obtain(handler);
                        msg.what = 1;
                        msg.obj = jsonResult;
                        msg.sendToTarget();
                        Log.e(TAG,jsonResult);
                    }
                }).start();

            }
        });
    }

    /**
     * change Handler to WeakReference or change to AsyncTask
     * */
    private static final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String json = msg.obj.toString();
                    int numberOfPicture = Constants.ApiBaiduMeinvHttpConstants.numberOfPicture;
                    ArrayList<String> picUrlList = JsonParser.getPicUrlFromJson(json,numberOfPicture);
                    StringBuilder stringBuilder = new StringBuilder();
                    for(String picUrl:picUrlList){
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
