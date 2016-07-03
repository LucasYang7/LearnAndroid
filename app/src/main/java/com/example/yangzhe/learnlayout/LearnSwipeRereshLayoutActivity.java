package com.example.yangzhe.learnlayout;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yangzhe.learnactivity.R;

import java.util.ArrayList;

public class LearnSwipeRereshLayoutActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_swipe_reresh_layout);

        mListView = (ListView)findViewById(R.id.listviewInSwipeRefreshLayout);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                getData()));

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayoutTest);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorSwipeRefreshLayoutRed,
                R.color.colorSwipeRefreshLayoutGreen,
                R.color.colorSwipeRefreshLayoutBlue);
    }

    private ArrayList<String> getData(){
        /*
        list.add("Hello");
        list.add("This is stormzhang");
        list.add("An Android Developer");
        list.add("Love Open Source");
        list.add("My GitHub: stormzhang");
        list.add("weibo: googdev");
        */
        return list;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },5000);
    }
}
