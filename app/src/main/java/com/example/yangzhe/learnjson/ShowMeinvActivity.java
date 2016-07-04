package com.example.yangzhe.learnjson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yangzhe.adapter.MeinvRecyclerViewAdapter;
import com.example.yangzhe.data.InternetImageData;
import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.support.Constants;
import com.example.yangzhe.support.HttpOperation;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

public class ShowMeinvActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ShowMeinvActivity";
    private SwipeRefreshLayout meinvSwipeRefreshLayout;
    private RecyclerView meinvRecyclerView;
    private GetMeinvPictureHandler getMeinvPictureHandler;
    ArrayList<InternetImageData> listInternetImageData = new ArrayList<InternetImageData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meinv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the RecyclerView
        meinvRecyclerView = (RecyclerView)findViewById(R.id.meinvRecyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        meinvRecyclerView.setHasFixedSize(true);
        meinvRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        // set an empty recyclerview to solve meinvSwipeRefreshLayout not showing in first time.
        meinvRecyclerView.setAdapter(new EmptyMeinvRecyclerViewAdapter());

        getMeinvPictureHandler = new GetMeinvPictureHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddMeinvPicture);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //getMeinvDataList();
            }
        });

        // set the SwipeRefreshLayout
        meinvSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.meinvSwipeRefreshLayout);
        meinvSwipeRefreshLayout.setOnRefreshListener(this);
        meinvSwipeRefreshLayout.setColorSchemeResources(R.color.colorSwipeRefreshLayoutRed,
                R.color.colorSwipeRefreshLayoutGreen,
                R.color.colorSwipeRefreshLayoutBlue);
    }

    public void getMeinvDataList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String httpUrl = Constants.ApiBaiduMeinvHttpConstants.httpUrl;
                String httpArg = Constants.ApiBaiduMeinvHttpConstants.httpArg;
                int numberOfPicture = Constants.ApiBaiduMeinvHttpConstants.numberOfPicture;
                String jsonResult = HttpOperation.request(httpUrl,httpArg,numberOfPicture);
                if(jsonResult == null){
                    jsonResult = Constants.ApiBaiduMeinvHttpConstants.failToGetJson;
                    Message msg = Message.obtain(getMeinvPictureHandler);
                    msg.what = 0;
                    msg.obj = jsonResult;
                    msg.sendToTarget();
                }else{
                    Collections.reverse(listInternetImageData);
                    ArrayList<InternetImageData> listOnceInternetImageData = JsonParser.getInternetPicInfoFromJson(
                            jsonResult,numberOfPicture);
                    listInternetImageData.addAll(listOnceInternetImageData);

                    //test
                    for(InternetImageData internetImageData:listInternetImageData){
                        Log.e(TAG,internetImageData.getPicUrl() + "\t" + internetImageData.getTitle());
                    }
                    //test

                    Collections.reverse(listInternetImageData);
                    Message msg = Message.obtain(getMeinvPictureHandler);
                    msg.what = 100;
                    msg.sendToTarget();
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        meinvSwipeRefreshLayout.setRefreshing(true);
        getMeinvDataList();
    }

    private class GetMeinvPictureHandler extends Handler{
        WeakReference<ShowMeinvActivity> weakRefActivity = null;
        public GetMeinvPictureHandler(ShowMeinvActivity context){
            weakRefActivity = new WeakReference<ShowMeinvActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    meinvSwipeRefreshLayout.setRefreshing(false);
                    String failToGetJson = msg.obj.toString();
                    Toast.makeText(weakRefActivity.get(),failToGetJson,Toast.LENGTH_SHORT).show();
                    break;

                case 100:
                    meinvSwipeRefreshLayout.setRefreshing(false);
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
                    break;

                default:
                    break;
            }
        }
    }

}


class EmptyMeinvRecyclerViewAdapter extends RecyclerView.Adapter<MeinvRecyclerViewHolder>{

    @Override
    public MeinvRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MeinvRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}