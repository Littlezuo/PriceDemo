package com.jaydenxiao.common.commonutils;

import com.orhanobut.hawk.Hawk;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/22.
 * 有储存时间的key
 */

public class HawkUtil {
    private static String TIME = "_time";


    /**
     * @param key
     * @param value
     * @param saveTime 单位 秒
     * @param <T>
     * @return
     */
    public static <T> boolean put(String key, T value, int saveTime) {
        boolean put = Hawk.put(key, value);
        long time = saveTime + System.currentTimeMillis() / 1000;
        Hawk.put(key + TIME, time);
        return put;
    }

    /**
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public static <T> boolean put(String key, T value) {
        boolean put = Hawk.put(key, value);
        return put;
    }

    /**
     * @param key
     * @param value
     * @param saveTime 单位 秒
     * @param <T>
     * @return
     */
    public static <T> void putAsync(final String key, final T value, final int saveTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Hawk.put(key, value);
                long time = saveTime + System.currentTimeMillis() / 1000;
                Hawk.put(key + TIME, time);
            }
        }).start();
    }

    public static <T> void putAsync(final String key, final T value) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Hawk.put(key, value);
            }
        }).start();
    }


    public static <T> T get(String key) {
        Long lastTime = Hawk.get(key + TIME);
        if (null != lastTime) {
            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime - lastTime < 0) {
                return Hawk.get(key);
            } else {
                return null;
            }
        } else {
            return Hawk.get(key);
        }
    }

    public static <T> void getAsync(final String key, Subscriber<T> subscriber) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                T t = Hawk.get(key);
                subscriber.onNext(t);
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
    public static <T> void getAsync(final String key, Action1<T> action1) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                T t = Hawk.get(key);
                subscriber.onNext(t);
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }


    public static void delete(String key) {
        Hawk.delete(key);
        Hawk.delete(key + TIME);
    }
}
