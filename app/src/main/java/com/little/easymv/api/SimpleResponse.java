package com.little.easymv.api;

import com.jaydenxiao.common.baserx.BaseResponse;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class SimpleResponse<T> extends BaseResponse<T> {
//    public String   code;
//    public String  msg;
    public String   state;


    public boolean isSuccess() {
        return "1".equals(state);
//        return 1==state;
    }

    @Override
    public boolean isOk() {
        return super.isOk();
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "code='"  + '\'' +
                ", msg='"  + '\'' +
                '}';
    }

}
