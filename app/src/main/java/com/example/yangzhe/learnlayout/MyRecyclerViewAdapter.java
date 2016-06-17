package com.example.yangzhe.learnlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzhe.learnactivity.R;

import java.util.List;

/**
 * Created by yangzhe on 16-6-16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private List<String> itemList;
    private Context context;

    public MyRecyclerViewAdapter(Context context,List<String> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view,parent,false);
        return new MyRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.textViewInCardView.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
