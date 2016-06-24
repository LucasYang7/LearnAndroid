package com.example.yangzhe.learnjson;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

/**
 * Created by yangzhe on 16-6-24.
 */
public class MeinvRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageViewCardPicture;
    public TextView textViewInCardView;

    public MeinvRecyclerViewHolder(View itemView){
        super(itemView);
        imageViewCardPicture = (ImageView)itemView.findViewById(R.id.imageViewShowCardPicture);
        textViewInCardView = (TextView)itemView.findViewById(R.id.textViewInCardView);
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

    }
}
