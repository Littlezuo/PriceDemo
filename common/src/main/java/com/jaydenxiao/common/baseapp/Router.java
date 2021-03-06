package com.jaydenxiao.common.baseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.jaydenxiao.common.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wanglei on 2016/11/29.
 */
public class Router {

    private Intent intent;
    private Activity from;
    private Class<?> to;
    private Bundle data;
    private int requestCode = -1;
    private int enterAnim = R.anim.anim_fragment_in;
    private int exitAnim = R.anim.anim_fragment_out;

    public static final int RES_NONE = -1;

    private static RouterCallback callback;

    private Router(Activity from) {
        this.from = from;
        intent = new Intent();
    }

    public static Router from(Activity from) {
        return new Router(from);
    }

    public static Router from() {
        return new Router(AppManager.getAppManager().currentActivity());
    }

    public static Router fromWithAnim() {
        return new Router(AppManager.getAppManager().currentActivity()).anim(R.anim.anim_fragment_in, R.anim.anim_fragment_out);
    }


    public Router to(Class<?> to) {
        this.to = to;
        return this;
    }

    public Router data(Bundle data) {
        this.data = data;
        return this;
    }

    public Router putByte(@Nullable String key, byte value) {
        getBundleData().putByte(key, value);
        return this;
    }

    public Router putChar(@Nullable String key, char value) {
        getBundleData().putChar(key, value);
        return this;
    }

    public Router putString(@Nullable String key, String value) {
        getBundleData().putString(key, value);
        return this;
    }

    public Router putShort(@Nullable String key, short value) {
        getBundleData().putShort(key, value);
        return this;
    }

    public Router putInt(@Nullable String key, int value) {
        getBundleData().putInt(key, value);
        return this;
    }

    public Router putFloat(@Nullable String key, float value) {
        getBundleData().putFloat(key, value);
        return this;
    }

    public Router putCharSequence(@Nullable String key, @Nullable CharSequence value) {
        getBundleData().putCharSequence(key, value);
        return this;
    }

    public Router putParcelable(@Nullable String key, @Nullable Parcelable value) {
        getBundleData().putParcelable(key, value);
        return this;
    }

    public Router putParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        getBundleData().putParcelableArray(key, value);
        return this;
    }

    public Router putParcelableArrayList(@Nullable String key,
                                         @Nullable ArrayList<? extends Parcelable> value) {
        getBundleData().putParcelableArrayList(key, value);
        return this;
    }


    public Router putIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        getBundleData().putIntegerArrayList(key, value);
        return this;
    }

    public Router putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        getBundleData().putStringArrayList(key, value);
        return this;
    }

    public Router putCharSequenceArrayList(@Nullable String key,
                                           @Nullable ArrayList<CharSequence> value) {
        getBundleData().putCharSequenceArrayList(key, value);
        return this;
    }

    public Router putSerializable(@Nullable String key, @Nullable Serializable value) {
        getBundleData().putSerializable(key, value);
        return this;
    }


    public Router requestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public Router anim(int enterAnim, int exitAnim) {
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
        return this;
    }

    public void launch() {
        launch(false);
    }

    private Bundle getBundleData() {
        if (data == null) {
            data = new Bundle();
        }
        return data;
    }

    public static void pop(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.anim_fragment_close_in, R.anim.anim_fragment_close_out);
    }

    public static void setCallback(RouterCallback callback) {
        Router.callback = callback;
    }

    public void launch(boolean finishCurrentActivity) {
        try {
            if (intent != null && from != null && to != null) {

                if (callback != null) {
                    callback.onBefore(from, to);
                }

                intent.setClass(from, to);

                intent.putExtras(getBundleData());

                if (requestCode < 0) {
                    from.startActivity(intent);
                    if (finishCurrentActivity) {
                        from.finish();
                    }
                    //                    from.finish();
                } else {
                    from.startActivityForResult(intent, requestCode);
                }

                if (enterAnim > 0 && exitAnim > 0) {
                    from.overridePendingTransition(enterAnim, exitAnim);
                }

                if (callback != null) {
                    callback.OnNext(from, to);
                }
            }
        } catch (Throwable throwable) {
            if (callback != null) {
                callback.onError(from, to, throwable);
            }
        }
    }

    interface RouterCallback {

        void onBefore(Activity from, Class<?> to);

        void OnNext(Activity from, Class<?> to);

        void onError(Activity from, Class<?> to, Throwable throwable);

    }


    public static String getString(Activity context, String key, String defaultValue) {
        String value = defaultValue;
        //        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = context.getIntent();
        if (intent != null) {
            value = intent.getStringExtra(key);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.getString(key, defaultValue);
            }
        }

        return value;
    }

    /**
     * 默认返回-1
     *
     * @param key
     * @return
     */
    public static Integer getInt(String key) {
        return getInt(key, -1);
    }

    public static Integer getInt(Activity activity, String key, int defaultValue) {
        int value = defaultValue;
        //        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = activity.getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                value = extras.getInt(key, defaultValue);
            }
        }
        return value;
    }

    public static Integer getInt(String key, int defaultValue) {
        return getInt(AppManager.getAppManager().currentActivity(), key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return getString(AppManager.getAppManager().currentActivity(), key, defaultValue);
    }

    public static String getString(String key) {
        return getString(AppManager.getAppManager().currentActivity(), key, "");
    }

}
