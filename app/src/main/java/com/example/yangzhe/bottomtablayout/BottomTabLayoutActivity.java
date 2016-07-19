package com.example.yangzhe.bottomtablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzhe.bottomtablayout.adapter.CustomeFragmentPagerAdapter;
import com.example.yangzhe.learnactivity.R;

public class BottomTabLayoutActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabLayout.Tab oneTab;
    private TabLayout.Tab twoTab;
    private TabLayout.Tab threeTab;
    private TabLayout.Tab fourTab;
    private CustomeFragmentPagerAdapter mCustomeFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab_layout);
        initViews();
        initEvents();
    }

    public void initViews(){
        mCustomeFragmentPagerAdapter = new CustomeFragmentPagerAdapter(getSupportFragmentManager(),
                BottomTabLayoutActivity.this);
        mViewPager = (ViewPager)findViewById(R.id.viewPagerInBottomTabActivity);
        mViewPager.setAdapter(mCustomeFragmentPagerAdapter);
        mTabLayout = (TabLayout)findViewById(R.id.bottomTabLayout);
        mTabLayout.setupWithViewPager(mViewPager);  //将TabLayout与ViewPager关联起来，这样当其中一个滑动状态改变时，另一个也会跟着改变

        oneTab = mTabLayout.getTabAt(0);
        oneTab.setCustomView(mCustomeFragmentPagerAdapter.getTabView(0));

        twoTab = mTabLayout.getTabAt(1);
        twoTab.setCustomView(mCustomeFragmentPagerAdapter.getTabView(1));

        threeTab = mTabLayout.getTabAt(2);
        threeTab.setCustomView(mCustomeFragmentPagerAdapter.getTabView(2));

        fourTab = mTabLayout.getTabAt(3);
        fourTab.setCustomView(mCustomeFragmentPagerAdapter.getTabView(3));

        setSelectedTabBackgroud(0);
        setUnSelectedTabBackgroud(1);
        setUnSelectedTabBackgroud(2);
        setUnSelectedTabBackgroud(3);

    }

    public void initEvents(){
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab == mTabLayout.getTabAt(0)){
                    setSelectedTabBackgroud(0);
                }else if(tab == mTabLayout.getTabAt(1)){
                    setSelectedTabBackgroud(1);
                }else if(tab == mTabLayout.getTabAt(2)){
                    setSelectedTabBackgroud(2);
                }else if(tab == mTabLayout.getTabAt(3)){
                    setSelectedTabBackgroud(3);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab == mTabLayout.getTabAt(0)){
                    setUnSelectedTabBackgroud(0);
                }else if(tab == mTabLayout.getTabAt(1)){
                    setUnSelectedTabBackgroud(1);
                }else if(tab == mTabLayout.getTabAt(2)){
                    setUnSelectedTabBackgroud(2);
                }else if(tab == mTabLayout.getTabAt(3)){
                    setUnSelectedTabBackgroud(3);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 设置被选中的tab的背景
     * */
    public void setSelectedTabBackgroud(int position){
        mViewPager.setCurrentItem(position);
        TabLayout.Tab tab = null;
        int drawableId = mCustomeFragmentPagerAdapter.drawableIds[position][1];
        switch(position){
            case 0:
                tab = oneTab;
                break;

            case 1:
                tab = twoTab;

                break;

            case 2:
                tab = threeTab;
                break;

            case 3:
                tab = fourTab;
                break;

            default:
                tab = oneTab;
                break;
        }

        ImageView imageView = (ImageView) tab.getCustomView()
                .findViewById(R.id.imageViewInBottomLayout);
        imageView.setImageResource(drawableId);
        TextView textView = (TextView) tab.getCustomView()
                .findViewById(R.id.textViewInBottomLayout);
        textView.setTextColor(getResources().getColor(R.color.colorSelectedTabText));
    }

    /**
     * 设置未被选中的tab的背景
     * */
    public void setUnSelectedTabBackgroud(int position){
        TabLayout.Tab tab = null;
        int drawableId = mCustomeFragmentPagerAdapter.drawableIds[position][0];
        switch(position){
            case 0:
                tab = oneTab;
                break;

            case 1:
                tab = twoTab;
                break;

            case 2:
                tab = threeTab;
                break;

            case 3:
                tab = fourTab;
                break;

            default:
                tab = oneTab;
                break;
        }

        ImageView imageView = (ImageView) tab.getCustomView()
                .findViewById(R.id.imageViewInBottomLayout);
        imageView.setImageResource(drawableId);
        TextView textView = (TextView) tab.getCustomView()
                .findViewById(R.id.textViewInBottomLayout);
        textView.setTextColor(getResources().getColor(R.color.colorTabText));
    }
}
