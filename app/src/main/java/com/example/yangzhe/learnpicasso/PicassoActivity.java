package com.example.yangzhe.learnpicasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.yangzhe.learnactivity.R;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {

    private ImageView imageViewPicasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        imageViewPicasso = (ImageView)findViewById(R.id.imageViewShowPicasso);
        Picasso.with(PicassoActivity.this)
                .load("http://tnfs.tngou.net/img/ext/151006/5666001e986c8a8c5f0fc8fdda457dba.jpg")
                //.resize(640,320)
                //.centerCrop()
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_error)
                .into(imageViewPicasso);
    }
}
