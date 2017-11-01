package com.github.JiangGuangxing.core;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
public class Result<T> {
    public static final String NET_ERROR = "网络异常，请稍后重试";
    private int code;
    private String msg;
    private T data;

    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode());
    }

    public static Result failed() {
        Result result = new Result(ResultCode.FAILED.getCode());
        result.setMsg(NET_ERROR);
        return result;
    }

    public static <T> Result success(T data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result failed(String msg) {
        Result result = new Result(ResultCode.FAILED.getCode());
        result.setMsg(msg);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ResultCode.FAILED.getCode());
    }

    public Result(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
