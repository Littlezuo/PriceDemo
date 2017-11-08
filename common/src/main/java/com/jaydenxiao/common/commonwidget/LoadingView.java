package com.jaydenxiao.common.commonwidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.R;


/**
 * description:弹窗浮动加载进度条
 * Created by xsf
 * on 2016.07.17:22
 */
public class LoadingView {
    private Context mContext;
    /**
     * 加载数据对话框
     */
    private Dialog mLoadingDialog;

    public LoadingView(Context context) {
        this(context, "加载中...");
    }

    public LoadingView(Context context, String msg) {
        if (context != null) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            if (TextUtils.isEmpty(msg)) {
                TextView loadingText = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
                loadingText.setText(msg);
            }
            mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
            mLoadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            //        mLoadingDialog.getWindow().setDimAmount(0);
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        }
    }


    public Dialog hasShadow(boolean hasShadow) {
        mLoadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return mLoadingDialog;
    }


    public LoadingView show() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
        return this;
    }

    public Dialog setCancalable(boolean cancalable) {
        if (mLoadingDialog != null) {
            mLoadingDialog.setCancelable(cancalable);
        }
        return mLoadingDialog;
    }


    public void show(Activity context) {
        if (context == null) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText("加载中...");
        mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
    }

    /**
     * 关闭加载对话框
     */
    public void cancel() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.cancel();
        }
    }
}
