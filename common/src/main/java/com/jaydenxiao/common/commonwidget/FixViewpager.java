package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Littlezuo on 2017/3/24.
 * 禁止页面滑动,且除去页面切换效果的viewpager
 */

public class FixViewpager extends ViewPager {
    private boolean ForbidSilde = true;

    public FixViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixViewpager(Context context) {
        super(context);
    }

//    //去除页面切换时的滑动翻页效果
//    @Override
//    public void setCurrentItem(int item, boolean smoothScroll) {
//        // TODO Auto-generated method stub
//        super.setCurrentItem(item, smoothScroll);
//    }
//
//    @Override
//    public void setCurrentItem(int item) {
//        // TODO Auto-generated method stub
//        super.setCurrentItem(item, false);
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (ForbidSilde)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);

    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (ForbidSilde)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

}
