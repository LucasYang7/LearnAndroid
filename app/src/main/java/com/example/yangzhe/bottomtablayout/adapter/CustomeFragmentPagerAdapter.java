package com.example.yangzhe.bottomtablayout.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.view.LayoutInflaterCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzhe.bottomtablayout.fragment.FourFragment;
import com.example.yangzhe.bottomtablayout.fragment.OneFragment;
import com.example.yangzhe.bottomtablayout.fragment.ThreeFragment;
import com.example.yangzhe.bottomtablayout.fragment.TwoFragment;
import com.example.yangzhe.learnactivity.R;

/**
 * Created by yangzhe on 16-7-19.
 */
public class CustomeFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private final String[] tabTitles = {"微信","通讯录","发现","我"};
    public final int[][] drawableIds = {
            {R.drawable.weixin,R.drawable.weixin_selected},
            {R.drawable.contacts,R.drawable.contacts_selected},
            {R.drawable.discovery,R.drawable.discovery_selected},
            {R.drawable.me,R.drawable.me_selected}};
    private Context mContext;

    public CustomeFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.mContext = context;
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.custome_bottom_tablayout,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageViewInBottomLayout);
        //imageView.setImageResource();
        TextView textView = (TextView) view.findViewById(R.id.textViewInBottomLayout);
        textView.setText(tabTitles[position]);
        return view;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment =  new OneFragment();
                break;

            case 1:
                fragment =  new TwoFragment();
                break;

            case 2:
                fragment =  new ThreeFragment();
                break;

            case 3:
                fragment =  new FourFragment();
                break;

            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
