package com.example.yangzhe.learnjson;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yangzhe.adapter.MeinvRecyclerViewAdapter;
import com.example.yangzhe.data.InternetImageData;
import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.support.Constants;
import com.example.yangzhe.support.HttpOperation;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ShowMeinvActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ShowMeinvActivity";
    private SwipeRefreshLayout meinvSwipeRefreshLayout;
    private RecyclerView meinvRecyclerView;
    private GetMeinvPictureHandler getMeinvPictureHandler;
    ArrayList<InternetImageData> listInternetImageData = new ArrayList<InternetImageData>();
    public static ArrayList<InternetImageData> staticListInternetImageData = new ArrayList<InternetImageData>();
    private MeinvRecyclerViewAdapter meinvRecyclerViewAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meinv);

        mContext = ShowMeinvActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the RecyclerView
        meinvRecyclerView = (RecyclerView)findViewById(R.id.meinvRecyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        meinvRecyclerView.setHasFixedSize(true);
        meinvRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        // set an empty recyclerview to solve meinvSwipeRefreshLayout not showing in first time.
        meinvRecyclerViewAdapter = new MeinvRecyclerViewAdapter(ShowMeinvActivity.this);
        meinvRecyclerViewAdapter.setItemList(listInternetImageData);
        meinvRecyclerView.setAdapter(meinvRecyclerViewAdapter);
        meinvRecyclerView.addOnScrollListener(new OnVerticalScrollListener2());

        getMeinvPictureHandler = new GetMeinvPictureHandler(ShowMeinvActivity.this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddMeinvPicture);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // set the SwipeRefreshLayout
        meinvSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.meinvSwipeRefreshLayout);
        meinvSwipeRefreshLayout.setOnRefreshListener(this);
        meinvSwipeRefreshLayout.setColorSchemeResources(R.color.colorSwipeRefreshLayoutRed,
                R.color.colorSwipeRefreshLayoutGreen,
                R.color.colorSwipeRefreshLayoutBlue);
        // auto refresh when into the ShowMeinvActivity
        meinvSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
               loadMeinvDataFromInternet();
            }
        });

    }//onCreate

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
                    //Collections.reverse(listInternetImageData);
                    ArrayList<InternetImageData> listOnceInternetImageData = JsonParser.getInternetPicInfoFromJson(
                            jsonResult,numberOfPicture);
                    listInternetImageData.addAll(listOnceInternetImageData);

                    //test
                    for(InternetImageData internetImageData:listInternetImageData){
                        Log.e(TAG,internetImageData.getPicUrl() + "\t" + internetImageData.getTitle());
                    }
                    //test

                    //Collections.reverse(listInternetImageData);
                    Message msg = Message.obtain(getMeinvPictureHandler);
                    msg.what = 100;
                    msg.sendToTarget();
                }
            }
        }).start();
    }

    /**
     * 从网站上下载妹子图片
     * */
    public void loadMeinvDataFromInternet(){
        meinvSwipeRefreshLayout.setRefreshing(true);
        getMeinvDataList();
    }

    @Override
    public void onRefresh() {
        loadMeinvDataFromInternet();
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
                        showMeinvActivity.meinvRecyclerViewAdapter.setItemList(showMeinvActivity
                                .listInternetImageData);
                        showMeinvActivity.meinvRecyclerViewAdapter.notifyDataSetChanged();
                        staticListInternetImageData = showMeinvActivity.listInternetImageData;
                        Toast.makeText(mContext,"从网站上下载了一些图片。。。",Toast.LENGTH_SHORT).show();
                    }
                    break;

                default:
                    break;
            }
        }
    }

    public static ArrayList<InternetImageData> getListInternetImageData(){
        return staticListInternetImageData;
    }

    /**
     * 为RecyclerView设置是否到达顶部和底部的事件监听
     * 通过canScrollVertically()函数来判断是否已经到达了顶部和底部
     * */
    class OnVerticalScrollListener1 extends RecyclerView.OnScrollListener{
        /*
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            //super.onScrolled(recyclerView, dx, dy);
            if(recyclerView.canScrollVertically(-1) == false){
                onScrolledToTop();
            }else if(recyclerView.canScrollVertically(1) == false){
                onScrolledToDown();
            }
        }

        public void onScrolledToTop(){
            Toast.makeText(mContext,"已经滑到顶部了",Toast.LENGTH_SHORT).show();
        }

        public void onScrolledToDown(){
            Toast.makeText(mContext,"已经滑到底部了，没有数据了",Toast.LENGTH_SHORT).show();
        }
        */

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(recyclerView.canScrollVertically(-1) == false && newState == RecyclerView.SCROLL_STATE_IDLE){
                Toast.makeText(mContext,"已经滑到顶部了",Toast.LENGTH_SHORT).show();
            }else if(recyclerView.canScrollVertically(1) == false && newState == RecyclerView.SCROLL_STATE_IDLE){
                Toast.makeText(mContext,"已经滑到底部了，没有图片了。。。",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 为RecyclerView设置是否到底部的事件监听
     * 通过staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions
     * 来找出最后一个完成出现的item
     * */
    class OnVerticalScrollListener2 extends RecyclerView.OnScrollListener{

        /**
         * 判断当前显示的item是否为RecyclerView中的最后一个item
         * */
        private boolean isLastItemDisplaying(RecyclerView recyclerView){
            if(recyclerView.getAdapter().getItemCount() != 0){
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager)
                        recyclerView.getLayoutManager();
                int [] lastCompletelyVisiblePostions = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(null);
                int lastCompletelyVisibleItemPosition = 0;   //找到当前页面最后完整显示的item的位置 　
                for(int i = 0;i < lastCompletelyVisiblePostions.length;i++){
                    if(lastCompletelyVisiblePostions[i] > lastCompletelyVisibleItemPosition){
                        lastCompletelyVisibleItemPosition = lastCompletelyVisiblePostions[i];
                    }
                }
                if (lastCompletelyVisibleItemPosition != RecyclerView.NO_POSITION &&
                        lastCompletelyVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                    return true;
            }
            return false;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            // RecyclerView已经滑到底部且RecyclerView处于静止状态
            if(isLastItemDisplaying(recyclerView) == true && newState == RecyclerView.SCROLL_STATE_IDLE){
                //Toast.makeText(mContext,"已经滑到底部了，没有图片了。。。",Toast.LENGTH_SHORT).show();
               loadMeinvDataFromInternet();
            }
        }
    }


}

/*
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
*/
