package com.little.easymv.api;

/**
 * Created by Administrator on 2017/3/20.
 */

public abstract class SimpleSubscriber<T> extends BaseSubscriber<SimpleResponse<T>> {

    @Override
    public void onNext(SimpleResponse<T> simpleRespose) {
        super.onNext(simpleRespose);
        if(simpleRespose.isOk()) {

        }
    }
}
