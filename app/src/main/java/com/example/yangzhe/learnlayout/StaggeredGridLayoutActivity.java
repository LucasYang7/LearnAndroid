package com.example.yangzhe.learnlayout;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.yangzhe.learnactivity.R;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);
        mRecyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
        // set RecyclerView's layout
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2
                ,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(StaggeredGridLayoutActivity.this,
                getListItemData()));
    }

    /**
     * get the text of each item
     * */
    private List<String> getListItemData(){
        List<String> listViewItems = new ArrayList<String>();
        listViewItems.add(new String("1984,George Orwell 1"));
        listViewItems.add(new String("Pride and Prejudice,Jane Austen 2"));
        listViewItems.add(new String("One Hundred Years of Solitude,Gabriel Garcia Marquez 3"));
        listViewItems.add(new String("The Book Thief,Markus Zusak 4"));
        listViewItems.add(new String("The Hunger Games,Suzanne Collins 5"));
        listViewItems.add(new String("The Hitchhiker's Guide to the Galaxy,Douglas Adams 6"));
        listViewItems.add(new String("The Theory Of Everything,Dr Stephen Hawking 7"));
        listViewItems.add(new String("1984,George Orwell 8"));
        listViewItems.add(new String("Pride and Prejudice,Jane Austen 9"));
        listViewItems.add(new String("One Hundred Years of Solitude,Gabriel Garcia Marquez 10"));
        listViewItems.add(new String("The Book Thief,Markus Zusak 11"));
        listViewItems.add(new String("The Hunger Games,Suzanne Collins 12"));
        listViewItems.add(new String("The Hitchhiker's Guide to the Galaxy,Douglas Adams 13"));
        listViewItems.add(new String("The Theory Of Everything,Dr Stephen Hawking 14"));
        listViewItems.add(new String("1984,George Orwell 15"));
        listViewItems.add(new String("Pride and Prejudice,Jane Austen 16"));
        listViewItems.add(new String("One Hundred Years of Solitude,Gabriel Garcia Marquez 17"));
        listViewItems.add(new String("The Book Thief,Markus Zusak 18"));
        listViewItems.add(new String("The Hunger Games,Suzanne Collins 19"));
        listViewItems.add(new String("The Hitchhiker's Guide to the Galaxy,Douglas Adams 20"));
        listViewItems.add(new String("The Theory Of Everything,Dr Stephen Hawking 21"));
        return listViewItems;
    }
}
