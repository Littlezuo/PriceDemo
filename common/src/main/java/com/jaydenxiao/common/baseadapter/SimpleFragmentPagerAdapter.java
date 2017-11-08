package com.jaydenxiao.common.baseadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Littlezuo on 2017/3/24.
 * 简单的传fragment的adapter
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragments;

    public SimpleFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragments == null) {
            throw new IllegalArgumentException("请传入Fragment");
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
