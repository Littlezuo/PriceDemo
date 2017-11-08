package com.jaydenxiao.common.basemvvm;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaydenxiao.common.baseevent.BindBus;
import com.jaydenxiao.common.baseevent.EventBusUtil;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.TUtil;

/**
 * Created by joyin on 17-4-12.
 */

public abstract class BaseFragment<M extends ViewModel> extends Fragment implements VMListener {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    private String mTitle;
    public RxManager mRxManager;
    public M model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }


    }
    protected View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        if (rootView==null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
//        }
        mContext = (BaseActivity) getActivity();
        mRxManager = new RxManager();
        if(this.getClass().isAnnotationPresent(BindBus.class)) {
            EventBusUtil.register(this);
//            RxBus.getDefault().register(this);
        }
        //默认绑定mv
        if(!this.getClass().isAnnotationPresent(UnbindMV.class)) {
            setModel();
        }
        isPrepared = true;
        isGolazyLoad();
//        LogUtils.loge("onCreateView ==" + this);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LogUtils.loge("onViewCreated ==" + this);
        initData();
    }

    public void initData() {

    }


    /**
     * 获取布局,同时设置isMVP,默认是MVP模式
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutResource();

    protected boolean isPrepared = false;
    public boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            isGolazyLoad();
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    private void isGolazyLoad() {
        LogUtils.logd("isGolazyLoad");
        if (!isPrepared || !isVisible) {
            return;
        }
        lazyLoad();
        isPrepared = false;
    }

    protected void lazyLoad() {
    }



    protected void onInvisible() {

    }

    protected void onVisible() {

    }

    protected BaseActivity mContext;

    protected void setModel() {
        model = TUtil.getT(this, 0);
        if (model != null) {
            model.onStart();
//            mContext = (BaseActivity) getActivity();
            model.mContext = mContext;
            model.mFragment = this;
            model.mRxManager = mRxManager;
//            model.isVisiable = isVisible;
            model.rootView = rootView;
            model.setVMListener(this);
        } else {
            throw new IllegalStateException("检查fragment是否viewmodel");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        if(this.getClass().isAnnotationPresent(BindBus.class)) {
            EventBusUtil.unregister(this);
//            RxBus.getDefault().unRegister(this);
        }
        if (model != null) {
            model.onDestory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public void onUpdate(int flag) {

    }
}
