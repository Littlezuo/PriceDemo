package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.baseapp.BaseApplication;


/**
 * Created by Littlezuo on 2017/8/4.
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    public MySwipeRefreshLayout(Context context) {
        super(context);
        init();
    }



    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setColorSchemeColors(BaseApplication.getAppColor(R.color.colorPrimaryDark));

    }

    public void initRefresh(final boolean isRefresh) {
        this.post(new Runnable() {
            @Override
            public void run() {
                setRefreshing(isRefresh);
            }
        });
    }

}
