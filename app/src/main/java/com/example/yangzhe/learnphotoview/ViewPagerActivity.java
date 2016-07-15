package com.example.yangzhe.learnphotoview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzhe.learnactivity.R;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

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
        /*
        private static final int[] sDrawables = {R.drawable.ic_empty,
                R.drawable.ic_error,
                R.drawable.ic_stub};
        */
        private ArrayList<Integer> mDrawableList = new ArrayList<Integer>();
        public SamplePagerAdapter(){
            mDrawableList.add(R.drawable.ic_empty);
            mDrawableList.add(R.drawable.ic_stub);
            mDrawableList.add(R.drawable.ic_error);
            mDrawableList.add(R.mipmap.ic_launcher);
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
            photoView.setImageResource(mDrawableList.get(position));

            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public int getCount() {
            return mDrawableList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
