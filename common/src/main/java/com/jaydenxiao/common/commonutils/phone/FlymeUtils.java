package com.jaydenxiao.common.commonutils.phone;

import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created by Littlezuo on 2017/3/24.
 * 检测Flyme
 */


public class FlymeUtils {
    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }
}
