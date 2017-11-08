package com.jaydenxiao.common.baseadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Littlezuo on 2017/3/23.
 * 简单的viewpager的adapter
 */
public class SimpelePagerAdapter extends PagerAdapter {
    ArrayList<ImageView> mImageViews ;
    public SimpelePagerAdapter(ArrayList<ImageView> images) {
        mImageViews = images;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
