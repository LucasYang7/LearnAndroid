package com.example.yangzhe.learnrxjava;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangzhe.learnactivity.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LearnRxJavaActivity extends AppCompatActivity {
    private final String TAG = "LearnRxJava";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rx_java);
        imageView = (ImageView)findViewById(R.id.imageViewShowRxJava);
        //showStringInRxJava();
        testActionInterface();
        testScheduler();
    }

    /**
     * create Observer in RxJava,Observer handle Event
     * */
    Observer<String> createObsever(){
        Observer<String> observer = new Observer<String>(){

            @Override
            public void onNext(String s) {          // like the onClick() method in OnClickListener
                Log.e(TAG,s);
            }

            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"onError");
            }

        };
        return observer;
    }

    /**
     * create Observable object,OnSubscribe looks like a plan.
     * call() method in OnSubscribe will be invoked automatically when observable subscribed by observer.
     * */
    Observable createObservable(){
        // (1)create observable use create() method
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");           // Observable produce Event
                subscriber.onNext("world");
                subscriber.onNext("I am RxJava!");
                subscriber.onCompleted();
            }
        });

        // (2)create observable use just() method
        Observable observable2 = Observable.just("one","two","three");

        // (3)create observable use from() method
        String[] strings = {"Wo","shi","RxJava"};
        Observable observable3 = Observable.from(strings);

        return observable1;
    }

    public void showStringInRxJava(){
        Observer<String> observer = createObsever();
        Observable observable = createObservable();
        observable.subscribe(observer);
    }

    public void testActionInterface(){
        // onNext()
        Action1<String> onNextAction = new Action1<String>(){

            @Override
            public void call(String s) {
                Log.e(TAG,s);
            }
        };

        // onError()
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG,"onError()!");
            }
        };

        // onCompleted
        Action0 onCompletedAction = new Action0(){

            @Override
            public void call() {
                Log.e(TAG,"onCompleted()!");
            }
        };

        Observable observable = createObservable();
        observable.subscribe(onNextAction); // subscribe support part of callback methods eg. onNext() only

    }

    public void testScheduler(){
        /*
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.e(TAG, "number:" + number);
                    }
                });
        */
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())            // 指定subscribe()放生在IO线程（产生事件）
                .observeOn(AndroidSchedulers.mainThread())   // 指定观察者Subscriber的回调函数(消费事件)发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(LearnRxJavaActivity.this,integer.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
