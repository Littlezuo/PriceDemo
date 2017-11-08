package com.jaydenxiao.common.baseevent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Littlezuo on 2017/8/25.
 */

public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    public static void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }
}
