package com.jaydenxiao.common.basemvvm;

import android.view.View;

import com.jaydenxiao.common.baseevent.BindBus;
import com.jaydenxiao.common.baseevent.EventBusUtil;
import com.jaydenxiao.common.baserx.RxManager;

//import com.jaydenxiao.common.baseevent.rxbus.RxBus;

/**
 * Created by Littlezuo on 2017/5/17.
 * 传该类作为泛型需手动注册rxbus(@BindBus)
 *
 */

public class ViewModel  {
//    public static boolean isErr = false;
    public boolean isErr = false;
    public static final int DEFAULT = 0x111;
    public static final int REFRESH = 0x222;
    public static final int LOADMORE = 0x333;
    public static final int ERROR = 0x333;
    public BaseActivity mContext;
    public RxManager mRxManager;
    public View rootView;
    public BaseFragment mFragment;
    //    public boolean isVisiable = true;

    public void onDestory() {
        setVMListener(null);
        if (mRxManager != null) {
            mRxManager.clear();
        }
        if(this.getClass().isAnnotationPresent(BindBus.class)) {
                        EventBusUtil.register(this);
//            RxBus.getDefault().unRegister(this);
        }
    }

    public void onStart() {
//        LogUtils.loge("base-onstart - mv");
        if(this.getClass().isAnnotationPresent(BindBus.class)) {
                        EventBusUtil.register(this);
//            RxBus.getDefault().register(this);
        }

    }

    public VMListener mVMListener;

    public void setVMListener(VMListener VMListener) {
        mVMListener = VMListener;
    }
}
