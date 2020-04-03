package com.example.yangzhe.learnjson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzhe.data.InternetImageData;
import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.learnphotoview.PhotoViewActivity;

import java.util.ArrayList;

/**
 * Created by yangzhe on 16-6-24.
 */
public class MeinvRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = "MeinvRecyclerViewHolder";
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
        Context context = v.getContext();
        ArrayList<InternetImageData> internetImageDataArrayList =  ShowMeinvActivity.getListInternetImageData();
        int positon = getAdapterPosition();       //图片在图片列表中的位置
        Bundle bundle = new Bundle();
        bundle.putInt("positon",positon);
        bundle.putString("WhichActivity","ShowMeinvActivity");
        bundle.putParcelableArrayList("internetImageDataArrayList",internetImageDataArrayList); // 网络图片的内容
        Intent intent = new Intent(context,PhotoViewActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
