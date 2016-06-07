package com.example.yangzhe.learnhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

public class HanderMessageActivity extends AppCompatActivity {

    private Button btnSendEmptyMessage;
    //private Button btnSendEmptyMessageAtTime;
    private Button btnSendEmptyMessageDelay;
    private Button btnSendMessage;
    //private Button btnSendMessageAtTime;
    private Button btnSendMessageDelay;
    private TextView txtViewShowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hander_message);
        btnSendEmptyMessage = (Button)findViewById(R.id.buttonSendEmptyMessage);
        //btnSendEmptyMessageAtTime = (Button)findViewById(R.id.buttonSendEmptyMessageAtTime);
        btnSendEmptyMessageDelay = (Button)findViewById(R.id.buttonSendEmptyMessageDelayed);
        btnSendMessage = (Button)findViewById(R.id.buttonSendMessage);
        //btnSendMessageAtTime = (Button)findViewById(R.id.buttonSendMessageAtTime);
        btnSendMessageDelay = (Button)findViewById(R.id.buttonSendMessageDelay);
        txtViewShowMessage = (TextView)findViewById(R.id.textViewShowMessage);
        btnSendEmptyMessage.setOnClickListener(clickListener);
        //btnSendEmptyMessageAtTime.setOnClickListener(clickListener);
        btnSendEmptyMessageDelay.setOnClickListener(clickListener);
        btnSendMessage.setOnClickListener(clickListener);
        //btnSendMessageAtTime.setOnClickListener(clickListener);
        btnSendMessageDelay.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.buttonSendEmptyMessage:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = Message.obtain();
                            msg.what = 0;
                            msg.obj = "This is from buttonSendEmptyMessage";
                            handler.sendEmptyMessage(msg.what);
                        }
                    }).start();
                    break;

                case R.id.buttonSendEmptyMessageDelayed:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = Message.obtain();
                            msg.what = 1;
                            msg.obj = "This is from buttonSendEmptyMessageDelayed";
                            handler.sendEmptyMessageDelayed(msg.what,2000);
                        }
                    }).start();
                    break;

                case R.id.buttonSendMessage:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = Message.obtain();
                            msg.what = 2;
                            msg.obj = "This is from buttonSendMessage";
                            handler.sendMessage(msg);
                        }
                    }).start();
                    break;

                case R.id.buttonSendMessageDelay:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = Message.obtain(handler);
                            msg.what = 3;
                            msg.obj = "This is from buttonSendMessageDelay and use sendToTarget";
                            msg.sendToTarget();
                        }
                    }
                    ).start();
                    break;

                default:
                    break;
            }

        }
    };

    /**
     * This Handler is bind to UI thread
     * */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what < 2){
                txtViewShowMessage.setText(msg.what + " This is an empty message,it only contains msg.what field.");
            }else{
                txtViewShowMessage.setText(msg.what + " " + msg.obj.toString());
            }

        }
    };

}
