package com.github.JiangGuangxing.core;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
public enum ResultCode {
    SUCCESS(0), FAILED(1);
    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
