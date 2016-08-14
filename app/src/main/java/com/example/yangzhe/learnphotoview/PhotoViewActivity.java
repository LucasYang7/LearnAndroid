package com.example.yangzhe.learnphotoview;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangzhe.data.InternetImageData;
import com.example.yangzhe.learnactivity.MainActivity;
import com.example.yangzhe.learnactivity.R;
import com.example.yangzhe.learndatabase.MyDatabaseHelper;
import com.example.yangzhe.learnjson.ShowMeinvActivity;
import com.example.yangzhe.learnpicasso.PicassoActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    //private static ProgressBar mProgressBar;
    private static TextView mText;
    private String whichActivity;
    private ArrayList<InternetImageData> internetImageDataArrayList = new ArrayList<InternetImageData>();
    private static Context mContext;
    MenuItem mFavorMenuItem;
    MenuItem mUnFavorMenuItem;
    private static ArrayList<String> meinvUrlList = new ArrayList<String>();
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase mSQLiteDatabase;
    int mPosition;
    String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_photo_view);
        mViewPager = (ViewPager)findViewById(R.id.view_pager2);
        //mProgressBar = (ProgressBar)findViewById(R.id.progressBarInPhotoView);
        mText = (TextView)findViewById(R.id.textViewInPhotoView);

        Bundle bundle = getIntent().getExtras();
        whichActivity = bundle.getString("WhichActivity");   // 判断是由哪个Activity跳转到了当前的Activity
        if(whichActivity.equals("ShowMeinvActivity")){
            internetImageDataArrayList = bundle.getParcelableArrayList("internetImageDataArrayList");
            int position = bundle.getInt("positon");
            mViewPager.setAdapter(new SamplePagerAdapter3(internetImageDataArrayList));
            mViewPager.setCurrentItem(position);            //指定ViewPager显示的初始化下标(下标值从0开始)
        }else{
            mViewPager.setAdapter(new SamplePagerAdapter2());
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    boolean isFavorited = false;
                    mPosition = position;
                    int index = position+1;
                    int total = meinvUrlList.size();
                    String url = meinvUrlList.get(position);

                    Cursor cursor = mSQLiteDatabase.query("Favorites",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            String tempUrl = cursor.getString(cursor.getColumnIndex("pictureUrl"));
                            if(tempUrl.equals(url)){    // already add favorites
                                isFavorited = true;
                                break;
                            }
                        }while(cursor.moveToNext());
                    }
                    cursor.close();

                    if(isFavorited == true){
                        resetUnFavorMenuItem();
                    }else{
                        resetFavorMenuItem();
                    }

                    Log.e("url",""+ index + "\t" + url);
                    //Resources resources = mContext.getResources();
                    //String text = String.format(resources.getString(R.string.index_photoview),index,total);
                    //text = text+"\t"+url;
                    String text = index + "/" + total + "\t" + url + "美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女";
                    mTitle = text;
                    mText.setText(text);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        // create database
        myDatabaseHelper = new MyDatabaseHelper(PhotoViewActivity.this,"MeituFavorites.db",null,1);
        mSQLiteDatabase = myDatabaseHelper.getReadableDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {  //为Toolbar上的返回按钮添加事件监听
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                switch(whichActivity){
                    case "ShowMeinvActivity":
                        intent.setClass(PhotoViewActivity.this, ShowMeinvActivity.class);
                        break;
                    case "MainActivity":
                        intent.setClass(PhotoViewActivity.this, MainActivity.class);
                        break;
                    default:
                        break;

                }

                startActivity(intent);
                */
                PhotoViewActivity.this.finish();
            }
        });

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
    }//onCreate()

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
            meinvUrlList = mMeinvList;

            // 在MeituActivity中，在onCreate处进行初始化
            int total = mMeinvList.size();
            String url = mMeinvList.get(0);
            //Resources resources = mContext.getResources();
            //String text = String.format(resources.getString(R.string.index_photoview),index,total);
            //text = text+"\t"+url;
            String text = 1 + "/" + total + "\t" + url + "美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女";
            mText.setText(text);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            /*
            if(position == 0){
                int index = position + 1;
                int total = mMeinvList.size();
                String url = mMeinvList.get(position);
                Log.e("url",""+position + "\t" + url);
                //Resources resources = mContext.getResources();
                //String text = String.format(resources.getString(R.string.index_photoview),index,total);
                //text = text+"\t"+url;
                String text = index + "/" + total + "\t" + url + "美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女美女";
                mText.setText(text);
            }
            */

            //mProgressBar.setVisibility(View.VISIBLE);
            Context context = container.getContext();
            // 尝试从xml加载PhotoView
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.photo_view,container,false);
            PhotoView photoView = (PhotoView)view.findViewById(R.id.photoviewInXml);
            final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBarInPhotoView2);
            final TextView textView = (TextView)view.findViewById(R.id.textViewInPhotoView2);
            final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photoView);
            container.addView(view, ViewPager.LayoutParams.MATCH_PARENT,
                   ViewPager.LayoutParams.MATCH_PARENT);
            Picasso.with(context)
                    .load(mMeinvList.get(position))
                    //.placeholder(R.drawable.ic_empty)
                    //.error(R.drawable.ic_empty)
                    .into(photoView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                            photoViewAttacher.update();
                        }

                        @Override
                        public void onError() {
                            progressBar.setVisibility(View.GONE);
                            textView.setVisibility(View.VISIBLE);
                        }
                    });
            return view;       // 这里返回ViewPager中一个item所对应的View
        }

        @Override
        public int getCount() {
            return mMeinvList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }//SamplePagerAdapter2

    static class SamplePagerAdapter3 extends PagerAdapter {

        private ArrayList<String> mMeinvList = new ArrayList<String>();
        public SamplePagerAdapter3(ArrayList<InternetImageData> internetImageDataArrayList){
            for(InternetImageData internetImageData:internetImageDataArrayList){
                mMeinvList.add(internetImageData.getPicUrl());
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //mProgressBar.setVisibility(View.VISIBLE);
            Context context = container.getContext();
            PhotoView photoView = new PhotoView(context);
            final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photoView);
            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);
            Picasso.with(context)
                    .load(mMeinvList.get(position))
                    //.placeholder(R.drawable.ic_empty)
                    .error(R.drawable.ic_empty)
                    .into(photoView, new Callback() {
                        @Override
                        public void onSuccess() {
                            //mProgressBar.setVisibility(View.GONE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.photo_view,menu);
        mFavorMenuItem = menu.findItem(R.id.action_favor);
        mUnFavorMenuItem = menu.findItem(R.id.action_unfavor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_favor:
                Toast.makeText(PhotoViewActivity.this,R.string.menu_favor,Toast.LENGTH_SHORT).show();
                //sharePicture(mPosition);
                ContentValues values = new ContentValues();
                values.put("pictureUrl",meinvUrlList.get(mPosition));
                values.put("title",mTitle);
                mSQLiteDatabase.insert("Favorites",null,values);               // insert
                resetUnFavorMenuItem();
                return true;

            case R.id.action_unfavor:
                Toast.makeText(PhotoViewActivity.this,R.string.menu_cancel_favor,Toast.LENGTH_SHORT).show();
                //downloadPicture(mPosition);
                mSQLiteDatabase.delete("Favorites","pictureUrl = ?",new String[]{meinvUrlList.get(mPosition)}); // delete
                resetFavorMenuItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void resetUnFavorMenuItem(){
        mFavorMenuItem.setVisible(false);
        mUnFavorMenuItem.setVisible(true);
    }

    public void resetFavorMenuItem(){
        mFavorMenuItem.setVisible(true);
        mUnFavorMenuItem.setVisible(false);
    }

}
