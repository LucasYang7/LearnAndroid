package com.example.yangzhe.learnhandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

public class HandlerActivity extends AppCompatActivity {

    private Button btnPostRunnable;
    private Button btnPostDelayRunnable;
    private Button btnRunOnUiThread;
    private Button btnPostRunnableFromSubHandler;
    private Button btnTestThreadLocal;
    private TextView textViewShowMessage;
    private Handler handler = new Handler(); //Handler is Thread Local Storage,its life cycle is different from Activity
    private SubHandler subHandler = new SubHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btnPostRunnable = (Button)findViewById(R.id.buttonPostRunnable);
        btnPostRunnable.setOnClickListener(click);

        btnPostDelayRunnable = (Button)findViewById(R.id.buttonPostDelayRunnable);
        btnPostDelayRunnable.setOnClickListener(click);

        btnRunOnUiThread = (Button)findViewById(R.id.buttonRunOnUiThread);
        btnRunOnUiThread.setOnClickListener(click);

        btnPostRunnableFromSubHandler = (Button)findViewById(R.id.buttonPostRunnableFromSubHandler);
        btnPostRunnableFromSubHandler.setOnClickListener(click);

        btnTestThreadLocal = (Button)findViewById(R.id.buttonTestThreadLocal);
        btnTestThreadLocal.setOnClickListener(click);

        textViewShowMessage = (TextView)findViewById(R.id.textViewShowRunnable);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewShowMessage.setText("This is runOnUiThread method() run On UI thread.");
            }
        });
    }

    // sub Handler post a Runnable execute
    private static class SubHandler extends Handler{

    }

    private View.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buttonPostRunnable:
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
                case R.id.buttonPostDelayRunnable:
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
                case R.id.buttonPostRunnableFromSubHandler:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            subHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewShowMessage.setText("This is a Runnable action from " +
                                            "SubHandler subHandler.");
                                }
                            });
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
                case R.id.buttonTestThreadLocal:
                    new ThreadLocalTest().printLog();
                    break;
                default:
                    break;
            }
        }
    };
}
