package com.example.yangzhe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzhe.data.InternetImageData;
import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.learnjson.MeinvRecyclerViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by yangzhe on 16-6-24.
 */
public class MeinvRecyclerViewAdapter extends RecyclerView.Adapter<MeinvRecyclerViewHolder> {
    private List<InternetImageData> itemList;
    private Context context;

    public MeinvRecyclerViewAdapter(Context context){
        this.context = context;
    }

    public MeinvRecyclerViewAdapter(Context context,List<InternetImageData> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    public void setItemList(List<InternetImageData> itemList){
        this.itemList = itemList;
    }

    @Override
    public MeinvRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view,
                parent,false);
        return new MeinvRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeinvRecyclerViewHolder holder, int position) {
        InternetImageData internetImageData = itemList.get(position);
        String url = internetImageData.getPicUrl();
        String title = internetImageData.getTitle();
        holder.textViewInCardView.setText(title);
        ImageLoader.getInstance().displayImage(url,holder.imageViewCardPicture);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
