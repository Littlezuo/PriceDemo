/*
package com.jaydenxiao.common.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.TUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.orhanobut.logger.Logger;


*/
/**
 * des:基类fragment
 * Created by xsf
 * on 2016.07.12:38
 *//*



public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {
    protected View rootView;
    public P mPresenter;
    public M mModel;
    public RxManager mRxManager;
    protected boolean isPrepared = false;
    protected boolean isMVP = true;
    //    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        //        mUnbinder = ButterKnife.bind(this, rootView);
        bindViews(rootView);
        mRxManager = new RxManager();
        if (isMVP) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            if (mPresenter != null) {
                mPresenter.mContext = this.getActivity();
            }
            initPresenter();
        }
        initView();
        isPrepared = true;
        isGolazyLoad();
        return rootView;
    }

    protected abstract void initView();

    protected abstract void bindViews(View rootView);


    protected void unableMVP() {
        this.isMVP = false;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {//onViewCreated在onCreateView执行完后立即执行
        super.onViewCreated(view, savedInstanceState);


    }

    protected boolean isVisible;

    */
/**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     *//*

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    protected void onVisible() {
        isGolazyLoad();
    }


    private void isGolazyLoad() {
        Logger.d("isGolazyLoad");
        if (!isPrepared || !isVisible) {
            return;
        }
        lazyLoad();
        isPrepared = false;
    }

    protected void lazyLoad() {
    };

    protected void onInvisible() {
    }


    */
/**
     * 获取布局,同时设置isMVP,默认是MVP模式
     *
     * @return
     *//*

    protected abstract int getLayoutResource();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public void initPresenter() {
    }

    //初始化view,只要new Fragment ,即使fragment不可见时也会执行
    //    protected void initView() {
    //    }

    */
/**
     * 通过Class跳转界面
     **//*

    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    */
/**
     * 通过Class跳转界面
     **//*

    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    */
/**
     * 含有Bundle通过Class跳转界面
     **//*

    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    */
/**
     * 含有Bundle通过Class跳转界面
     **//*

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    */
/**
     * 开启加载进度条
     *//*

    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    */
/**
     * 开启加载进度条
     *
     * @param msg
     *//*

    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(getActivity(), msg, true);
    }

    */
/**
     * 停止加载进度条
     *//*

    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }


    */
/**
     * 短暂显示Toast提示(来自String)
     **//*

    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    */
/**
     * 短暂显示Toast提示(id)
     **//*

    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    */
/**
     * 长时间显示Toast提示(来自res)
     **//*

    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    */
/**
     * 长时间显示Toast提示(来自String)
     **//*

    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }


    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    //    */
/**
    //     * 网络访问错误提醒
    //     *//*

    //    public void showNetErrorTip() {
    //        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(),R.drawable.ic_wifi_off);
    //    }
    //
    //    public void showNetErrorTip(String error) {
    //        ToastUitl.showToastWithImg(error,R.drawable.ic_wifi_off);
    //    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //        ButterKnife.unbind(this);
        //        mUnbinder.unbind();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
    }


}
*/
/***************
 * 使用例子
 *********************//*

//1.mvp模式
//public class SampleFragment extends BaseFragment<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleFragment extends BaseFragment {
//    @Override
//    public int getLayoutResource() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}


*/
