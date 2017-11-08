package com.jaydenxiao.common.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class MyBaseRespose<T> implements Serializable {
    public int code = 1;
    public String msg;

    public T data;

    public boolean success() {
        return 1== code;
    }

    @Override
    public String toString() {
        return "MyBaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
