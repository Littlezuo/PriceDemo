package com.jaydenxiao.common.baserx;

import java.io.Serializable;

/**
 * BaseResponse Data T
 * Created by Tamic on 2016-06-06.
 */
public class BaseResponse<T> implements Serializable{
    //结果码
    public int code = 1;
    /*错误信息:msg, error, message*/
    public String msg, error, message;
    /*真实数据 data或者result*/
    public T data, result, ret, body;

    public boolean isOk() {
        return true;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

//    @Override
//    public String toString() {
//        return "BaseResponse{" +
//                "code=" + code +
//                ", msg='" + msg + '\'' +
//                ", error='" + error + '\'' +
//                ", message='" + message + '\'' +
//                ", data=" + data +
//                '}';
//    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
