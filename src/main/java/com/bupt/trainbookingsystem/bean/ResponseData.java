package com.bupt.trainbookingsystem.bean;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    /**
     * 状态码
     * 0表示通用操作成功码
     * 1表示通用操作失败码
     * 100 用户名错误
     * 101 密码错误
     */
    private int code;

    /**
     * 错误消息
     * 成功时候为success
     */
    private String msg;

    /**
     * 结果数据
     */
    private T data;


    public static ResponseData success() {
        return success(null);
    }

    public static ResponseData success(Object data) {
        ResponseData result = new ResponseData();
        result.setCode(0);
        result.setMsg("SUCCESS");
        result.setData(data);
        return result;
    }


    public static ResponseData fail(String msg) {
        return fail(msg, null, 1);
    }

    public static ResponseData fail(String msg, int code){
        return fail(msg, null, code);
    }

    public static ResponseData fail(String msg, Object data, int code) {
        ResponseData result = new ResponseData();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }




    public ResponseData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
