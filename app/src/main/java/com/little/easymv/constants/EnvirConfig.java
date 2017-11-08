package com.little.easymv.constants;

import android.util.Log;

import com.jaydenxiao.common.commonutils.LogUtils;

/**
 * Created by Littlezuo on 2016/12/1.
 */

public class EnvirConfig {
    public static String bugly_appId;
    public static String umeng_appKey;

    public static void setEnvir(boolean env) {

        Log.e("kkkk", "生产环境 " + env);
        if (env) {  //生产环境
            UrlConstants.KaBu_BASE_URL =  "http://v2.api.dmzj.com/";
            ApiConstants.SPLASH_SEC = 2000;
            LogUtils.logInit(false);
            bugly_appId = "949900637b";
            umeng_appKey = "5909904ba325117c1a00100b";
        } else { //测试环境
            UrlConstants.KaBu_BASE_URL =  "http://v2.api.dmzj.com/";
            LogUtils.logInit(true);
            ApiConstants.SPLASH_SEC = 0;
            bugly_appId = "949900637b";
            umeng_appKey = "5909904ba325117c1a00100b";
        }
    }
}
