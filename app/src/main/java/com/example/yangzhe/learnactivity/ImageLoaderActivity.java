package com.example.yangzhe.learnactivity;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageLoaderActivity extends AppCompatActivity {

    private Button btnShowImageLoader;
    private ImageView imageViewShowImageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        // DisplayImageOptions are local for every display task
        // config the display image options
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageOnFail(R.drawable.ic_error)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        imageViewShowImageLoader = (ImageView)findViewById(R.id.imageViewShowImageLoader);
        btnShowImageLoader  = (Button)findViewById(R.id.buttonShowImageLoader);
        btnShowImageLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display the image from uri
                String uri = "http://tnfs.tngou.net/img/ext/160321/b148ab59b84e59dfd988da84aff1fbf1.jpg";
                ImageLoader.getInstance().displayImage(uri,imageViewShowImageLoader,options);
            }
        });

    }
}
