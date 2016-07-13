package com.example.yangzhe.learnretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yangzhe.learnactivity.R;
import com.google.gson.Gson;

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

        testGson();

    }

    public void testGson(){
        Gson gson = new Gson();
        int[] ints = {1,2,3,4,5};
        String[] strings = {"abc","def","ghi"};

        // Serialization
        String jsonResult = "";
        jsonResult = gson.toJson(strings);
        Log.e(TAG,jsonResult);
        jsonResult = gson.toJson(ints);
        Log.e(TAG,jsonResult);

        // Deserialization
        int[] ints2 = gson.fromJson(jsonResult,int[].class);
        for(int i:ints2){
            Log.e(TAG,String.valueOf(i));
        }

        // Object Serialization
        BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();
        Gson gson1 = new Gson();
        String json = gson1.toJson(bagOfPrimitives);
        Log.e(TAG,json);

        // Object Deserialization
        BagOfPrimitives bagOfPrimitives1 = gson.fromJson(json,BagOfPrimitives.class);
        String result = "value1: " + String.valueOf(bagOfPrimitives1.getValue1())
                +"\n value2: " + bagOfPrimitives1.getValue2()
                +"\n value3: " + bagOfPrimitives1.getValue3();
        Log.e(TAG,result);
    }
}

class BagOfPrimitives{
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;
    private int value4 = 4;
    private String value5 = "def";

    BagOfPrimitives(){

    }

    public int getValue1(){
        return value1;
    }

    public String getValue2(){
        return value2;
    }

    public int getValue3(){
        return value3;
    }
}
