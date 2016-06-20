package com.example.yangzhe.learnlayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.widget.RatioImageView;

/**
 * Created by yangzhe on 16-6-16.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //public RatioImageView imageViewCardPicture;
    public ImageView imageViewCardPicture;
    public TextView textViewInCardView;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        //imageViewCardPicture = (RatioImageView)itemView.findViewById(R.id.imageViewShowCardPicture);
        imageViewCardPicture = (ImageView)itemView.findViewById(R.id.imageViewShowCardPicture);
        textViewInCardView = (TextView)itemView.findViewById(R.id.textViewInCardView);
        //imageViewCardPicture.setOriginalSize(618,1000);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
