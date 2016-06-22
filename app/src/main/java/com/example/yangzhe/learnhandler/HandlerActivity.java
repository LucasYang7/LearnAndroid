package com.example.yangzhe.learnhandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

public class HandlerActivity extends AppCompatActivity {

    private Button btnPostMessage;
    private Button btnPostDelayMessage;
    private Button btnRunOnUiThread;
    private TextView textViewShowMessage;
    private Handler handler = new Handler(); //Handler is Thread Local Storage,its life cycle is different from Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        btnPostMessage = (Button)findViewById(R.id.buttonPostMessage);
        btnPostMessage.setOnClickListener(click);
        btnPostDelayMessage = (Button)findViewById(R.id.buttonPostDelayMessage);
        btnPostDelayMessage.setOnClickListener(click);
        btnRunOnUiThread = (Button)findViewById(R.id.buttonRunOnUiThread);
        btnRunOnUiThread.setOnClickListener(click);
        textViewShowMessage = (TextView)findViewById(R.id.textViewShowRunnable);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewShowMessage.setText("This is runOnUiThread method() run On UI thread.");
            }
        });
    }

    private View.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buttonPostMessage:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            /**
                             * Every Message will execute the Runnable action when handled by Handler
                             * post() will invoke getPostMessage(Runnable r),and assign the Runnable to a Message object.
                             * */
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewShowMessage.setText("Post an Runnable from work thread to" +
                                            " UI thread using post and then UI thread execute this action");
                                }
                            });
                        }
                    }).start();
                    break;
                case R.id.buttonPostDelayMessage:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // you can not update the UI in work thread,this will trigger an exception
                            // textViewShowMessage.setText("This is from postdelay function.");
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    textViewShowMessage.setText("Post an Runnable from work thread to" +
                                            " UI thread using postdealy and then UI thread execute this action in 5S later.");
                                }
                            },5000);
                        }
                    }).start();
                    break;
                case R.id.buttonRunOnUiThread:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // the below method run on ui thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textViewShowMessage.setText("This is runOnUiThread() method from buttonRunOnUiThread.");
                                }
                            });
                        }
                    }).start();
                    break;
                default:
                    break;
            }
        }
    };
}
