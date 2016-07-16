package com.example.yangzhe.learnphotoview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzhe.learnactivity.MainActivity;
import com.example.yangzhe.learnactivity.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mViewPager = (ViewPager)findViewById(R.id.view_pager2);
        mViewPager.setAdapter(new SamplePagerAdapter2());
        mViewPager.setCurrentItem(5);            //指定ViewPager显示的初始化下标(下标值从0开始)
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    static class SamplePagerAdapter2 extends PagerAdapter {

        private ArrayList<String> mMeinvList = new ArrayList<String>();
        public SamplePagerAdapter2(){
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t01.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t02.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t03.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t04.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t05.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t06.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t07.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t08.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t09.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t10.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t11.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t12.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t13.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t14.jpg");
            mMeinvList.add("http://pic.mmfile.net/2015/06/01t15.jpg");
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            PhotoView photoView = new PhotoView(context);
            final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photoView);
            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);
            Picasso.with(context)
                    .load(mMeinvList.get(position))
                    //.placeholder(R.drawable.ic_empty)
                    .error(R.drawable.ic_error)
                    .into(photoView, new Callback() {
                        @Override
                        public void onSuccess() {
                            photoViewAttacher.update();
                        }

                        @Override
                        public void onError() {

                        }
                    });
            return photoView;
        }

        @Override
        public int getCount() {
            return mMeinvList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
