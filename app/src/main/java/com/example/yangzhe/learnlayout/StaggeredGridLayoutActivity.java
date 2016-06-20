package com.example.yangzhe.learnlayout;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.yangzhe.data.AlbumImageData;
import com.example.yangzhe.learnactivity.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GetPictureHandler getPictureHandler;
    List<AlbumImageData> listAlbumImageData = new ArrayList<AlbumImageData>();   //store the picture information
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);
        mRecyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
        getPictureHandler = new GetPictureHandler(this);
        getListAlbumImageData();
    }


    /**
     * This Handler is used to get picture from phone
     * */
    private static class GetPictureHandler extends Handler {
        WeakReference<StaggeredGridLayoutActivity> weakRefActivity = null;
        public GetPictureHandler(StaggeredGridLayoutActivity context){
            weakRefActivity = new WeakReference<StaggeredGridLayoutActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 100){
                StaggeredGridLayoutActivity staggeredGridLayoutActivity = weakRefActivity.get();
                if(staggeredGridLayoutActivity == null)
                    return;
                else{
                    // set RecyclerView's layout and adapter
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2
                            ,StaggeredGridLayoutManager.VERTICAL);
                    staggeredGridLayoutActivity.mRecyclerView.setHasFixedSize(true);
                    staggeredGridLayoutActivity.mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                    staggeredGridLayoutActivity.mRecyclerView.setAdapter(new MyRecyclerViewAdapter(
                            staggeredGridLayoutActivity, staggeredGridLayoutActivity.listAlbumImageData));
                }
            }
        }
    }

    /**
     * get the information of each picture in the item
     * */
    private void getListAlbumImageData(){
        // get the picture name and picture path,and then store into List
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = getContentResolver();
                // get jpeg and png file ,desc by time
                Cursor cursor = contentResolver.query(uri,null,MediaStore.Images.Media.MIME_TYPE +
                                "=\"image/jpeg\" or " + MediaStore.Images.Media.MIME_TYPE + "=\"image/png\"",
                        null, MediaStore.Images.Media.DATE_MODIFIED + " desc");
                if(cursor != null){
                    listAlbumImageData.clear();
                    while(cursor.moveToNext()){
                        String picturePath = "file://" + cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        int width = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
                        int height = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
                        Log.e("picturePath",picturePath + " " + width + " " + height);
                        AlbumImageData albumImageData = new AlbumImageData(width,height,picturePath);
                        listAlbumImageData.add(albumImageData);
                    }
                }
                Message msg = Message.obtain();
                msg.what = 100;
                getPictureHandler.sendMessage(msg);
            }
        }).start();


    }
}
