package com.example.yangzhe.learnjson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.yangzhe.adapter.MeinvRecyclerViewAdapter;
import com.example.yangzhe.data.InternetImageData;
import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.support.Constants;
import com.example.yangzhe.support.HttpOperation;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ShowMeinvActivity extends AppCompatActivity {
    private static final String TAG = "ShowMeinvActivity";
    private RecyclerView meinvRecyclerView;
    private GetMeinvPictureHandler getMeinvPictureHandler;
    ArrayList<InternetImageData> listInternetImageData = new ArrayList<InternetImageData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meinv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        meinvRecyclerView = (RecyclerView)findViewById(R.id.meinvRecyclerView);

        getMeinvPictureHandler = new GetMeinvPictureHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddMeinvPicture);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                getMeinvDataList();
            }
        });
    }

    public void getMeinvDataList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String httpUrl = Constants.ApiBaiduMeinvHttpConstants.httpUrl;
                String httpArg = Constants.ApiBaiduMeinvHttpConstants.httpArg;
                int numberOfPicture = Constants.ApiBaiduMeinvHttpConstants.numberOfPicture;
                String jsonResult = HttpOperation.request(httpUrl,httpArg,numberOfPicture);
                listInternetImageData = JsonParser.getInternetPicInfoFromJson(
                        jsonResult,numberOfPicture);
                //test
                for(InternetImageData internetImageData:listInternetImageData){
                    Log.e(TAG,internetImageData.getPicUrl() + "\t" + internetImageData.getTitle());
                }
                //test

                Message msg = Message.obtain(getMeinvPictureHandler);
                msg.what = 100;
                msg.sendToTarget();
            }
        }).start();
    }

    private static class GetMeinvPictureHandler extends Handler{
        WeakReference<ShowMeinvActivity> weakRefActivity = null;
        public GetMeinvPictureHandler(ShowMeinvActivity context){
            weakRefActivity = new WeakReference<ShowMeinvActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 100){
                ShowMeinvActivity showMeinvActivity = weakRefActivity.get();
                if(showMeinvActivity == null)
                    return;
                else{
                    // set RecyclerView's layout and adapter
                    StaggeredGridLayoutManager staggeredGridLayoutManager =
                            new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                    showMeinvActivity.meinvRecyclerView.setHasFixedSize(true);
                    showMeinvActivity.meinvRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                    showMeinvActivity.meinvRecyclerView.setAdapter(new MeinvRecyclerViewAdapter(
                            showMeinvActivity,showMeinvActivity.listInternetImageData));
                }
            }
        }
    }

}
