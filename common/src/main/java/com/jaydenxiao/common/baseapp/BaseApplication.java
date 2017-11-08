package com.jaydenxiao.common.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

/**
 * APPLICATION
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程优先级,不与主线程抢资源
//                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                //初始化fresco
                Fresco.initialize(getAppContext());
                //初始化realm
//                initRealm();
                //初始化缓存
                Hawk.init(getAppContext())
                        .setEncryption(new NoEncryption())
                        .build();
                //蒲公英crash上报
                //PgyCrashManager.register(this);
                //初始化内存泄漏检测
                //LeakCanary.install(this);
                //初始化过度绘制检测
                //BlockCanary.install(this, new AppBlockCanaryContext()).start();
                initLeakCanary();
                Stetho.initializeWithDefaults(getAppContext());
            }
        }).start();
    }

    private void initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
    }

    public static Context getAppContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }

    public static String getStr(@StringRes int strRes) {
        return getAppResources().getString(strRes);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static int getAppColor(int colorId) {
        int color = getAppResources().getColor(colorId);
        return color;
    }
}
