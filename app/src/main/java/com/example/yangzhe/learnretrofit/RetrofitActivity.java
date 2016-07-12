package com.example.yangzhe.learnretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yangzhe.learnactivity.R;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //创建观察者Observer
        Observer<List<TaingouGallery>> observer = new Observer<List<TaingouGallery>>() {
            @Override
            public void onCompleted() {
              Log.e(TAG,"onCompleted() in RetrofitActivity.");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"onError() in RetrofitActivity.");
                Log.e(TAG,e.toString());
                e.printStackTrace();
            }

            @Override
            public void onNext(List<TaingouGallery> taingouGallerys) {
               for(TaingouGallery taingouGallery : taingouGallerys){
                   Log.e(TAG,taingouGallery.getDescription());
               }
            }
        };

        Network.getTngouAPI()
                .search("可爱")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
