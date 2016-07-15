package com.example.yangzhe.learnphotoview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzhe.learnactivity.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (HackyViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(new SamplePagerAdapter());
    }

    static class SamplePagerAdapter extends PagerAdapter{

        private ArrayList<String> mMeinvList = new ArrayList<String>();
        public SamplePagerAdapter(){
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
