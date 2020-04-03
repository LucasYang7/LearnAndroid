package com.example.yangzhe.learnhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
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
        btnSendMessageDelay = (Button)findViewById(R.id.buttonSendToTarget);
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

                case R.id.buttonSendToTarget:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = Message.obtain(handler2);       // obtain the Message from handler2
                            msg.what = 3;
                            msg.obj = "This is from handler2 and use sendToTarget() to send Message.";
                            msg.sendToTarget();         //send message to the specified handler
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
     * It is an anonymous inner class which extends Handler
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

    /**
     * This is another Handler which bind to UI thread
     * */
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            txtViewShowMessage.setText(msg.what + " " + msg.obj.toString());
        }
    };

    //test
    /**
     * handler3 is a Handler Object reference , it will not the HanderMessageActivity Object's reference
     * */
    private Handler handler3 = new Handler();

    /**
     * handler4 is anonymous object reference, the anonymous class extends Handler class
     * */
    private Handler handler4 = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
    };
    //test
}
