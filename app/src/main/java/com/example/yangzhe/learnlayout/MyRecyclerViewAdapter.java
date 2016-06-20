package com.example.yangzhe.learnlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangzhe.data.AlbumImageData;
import com.example.yangzhe.learnactivity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yangzhe on 16-6-16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private List<AlbumImageData> itemList;
    private Context context;
    // Here are something about ImageLoader
    // DisplayImageOptions are local for every display task
    // config the display image options
    /*
    private DisplayImageOptions options =  new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_stub)
            .showImageOnFail(R.drawable.ic_error)
            .showImageForEmptyUri(R.drawable.ic_stub)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .displayer(new RoundedBitmapDisplayer(20))
            .build();
    */
    public MyRecyclerViewAdapter(Context context,List<AlbumImageData> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    /**
     * create a new ViewHolder (invoked by the layout manager)
     * */
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view,parent,false);
        return new MyRecyclerViewHolder(itemView);
    }

    /**
     * replace the content of a ViewHolder (invoked by the layout manager)
     * */
    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, int position) {
        AlbumImageData albumImageData = itemList.get(position);
        String uri = albumImageData.getPath();
        int width = albumImageData.getWidth();       // get the width of original image
        int height = albumImageData.getHeight();     // get the height of original image
        Log.e("URI",uri + " " + position);
        holder.textViewInCardView.setText(uri);
        holder.imageViewCardPicture.setOriginalSize(width,height);   // set the original image's size
        ImageLoader.getInstance().displayImage(uri,holder.imageViewCardPicture);
    }

    /**
     * return the sizeof your data set hold by Adapter (invoked by the layout manager)
     * */
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
