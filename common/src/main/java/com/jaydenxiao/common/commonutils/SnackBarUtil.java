package com.jaydenxiao.common.commonutils;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.jaydenxiao.common.R;
import com.jaydenxiao.common.baseapp.AppManager;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Littlezuo on 2017/5/25.
 */

public class SnackBarUtil {

    public static final int ERR = 1;
    public static final int WARN = 2;
    public static final int CONFIRM = 3;
    public static final int INFO = 4;

    public static void showErr(@NonNull View view, String msg) {
        show(view, msg, ERR);
    }

    public static void showErr(Activity activity, String msg) {
        show(activity.findViewById(android.R.id.content), msg, ERR);
    }


    public static void show(Activity activity, String message, int level) {
//        activity.getWindow().getDecorView();
        show(activity.findViewById(android.R.id.content), message, level);
    }

    public static void show(View view, @StringRes int resId, int level) {
        show(view, view.getResources().getString(resId), level);
    }

    public static void show(View view, String message, int level) {
        TSnackbar snackbar = TSnackbar.make(view, "  " + message, TSnackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        switch (level) {
            case SnackBarUtil.ERR:
                snackbar.addIcon(R.drawable.ic_wrong, 96);
                snackbarView.setBackgroundColor(Color.parseColor("#FFF44336"));
                break;
            case SnackBarUtil.WARN:
                snackbar.addIcon(R.drawable.ic_warm, 96);
                snackbarView.setBackgroundColor(Color.parseColor("#FFFEC005"));
                break;
            case SnackBarUtil.INFO:
                snackbarView.setBackgroundColor(Color.parseColor("#FF2094F3"));
                //                snackbar.addIcon(R.drawable.ic_success, 250);
                break;
            case SnackBarUtil.CONFIRM:
                snackbar.addIcon(R.drawable.ic_success, 96);
                snackbarView.setBackgroundColor(Color.parseColor("#FF4CB04E"));
                break;
        }
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void showSafe(final String message, final int level) {
        Observable.just(message).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
               show(AppManager.getAppManager().currentActivity(),message,level);
            }
        });
    }

    public static void show(String message, int level) {
        show(AppManager.getAppManager().currentActivity(),message,level);
    }
}
