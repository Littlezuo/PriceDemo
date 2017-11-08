package com.little.easymv.api;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.jaydenxiao.common.commonutils.HawkUtil;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingView;
import com.little.easymv.app.MyApplication;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/20.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    public boolean showOnstart;
    public SwipeRefreshLayout RefreshLayout;
    public String cacheKey;
    public boolean isPostCache = false;
    public Context mContext;
    public String msg;
    public boolean showDialog = true;
    public LoadingView mDialog;


    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public BaseSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public BaseSubscriber(Context context, String msg, boolean showDialog, String cacheKey) {
        this.mContext = context;
        this.showDialog = showDialog;
        this.isPostCache = !TextUtils.isEmpty(cacheKey);
        this.cacheKey = cacheKey;
        this.msg = msg;
    }

    public BaseSubscriber(Context context, boolean showDialog, String cacheKey) {
        this(context, MyApplication.getAppContext().getString(com.jaydenxiao.common.R.string.loading), showDialog, cacheKey);
    }

    public BaseSubscriber(Context context) {
        this(context, MyApplication.getAppContext().getString(com.jaydenxiao.common.R.string.loading), true);
    }


    public BaseSubscriber(Context context, boolean showDialog) {
        this(context, MyApplication.getAppContext().getString(com.jaydenxiao.common.R.string.loading), showDialog);
    }

    public BaseSubscriber() {
        this(null, null, false);
    }

    public BaseSubscriber(SwipeRefreshLayout refreshLayout) {
        //        this(null,false,false);
        this(refreshLayout, false);
        this.RefreshLayout = refreshLayout;
    }

    public BaseSubscriber(SwipeRefreshLayout refreshLayout, boolean showOnStart) {
        //        this(null,false);
        this.RefreshLayout = refreshLayout;
        this.showOnstart = showOnStart;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            if (mDialog == null)
                mDialog = new LoadingView(mContext).show();
            mDialog.show();
        }

        if (showOnstart) {
            if (RefreshLayout != null) {
                RefreshLayout.setRefreshing(true);
            }
        }
    }

    @Override
    public void onNext(final T data) {
        _onNext((T) data);
        closeRefresh();
    }

    private void closeRefresh() {
        if (RefreshLayout != null) {
            RefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    RefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void onError(Throwable e) {
        closeRefresh();
        if (showDialog) {
            mDialog.cancel();
        }
        if (isPostCache) {
            HawkUtil.getAsync(cacheKey, new Action1<T>() {
                @Override
                public void call(T t) {
                    _onNext(t);
                }
            });
        }
        _onError(e.getMessage());
    }

    @Override
    public void onCompleted() {
        if (showDialog) {
            mDialog.cancel();
        }
    }

    protected abstract void _onNext(T t);

    protected void _onError(String message) {
        ToastUitl.showSafeShort(message);
        LogUtils.loge("message = " + message);
        //                SnackBarUtil.showSafe(message,SnackBarUtil.WARN);
    }
}
