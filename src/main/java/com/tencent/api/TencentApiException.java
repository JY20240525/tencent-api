package com.tencent.api;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 平台 API 调用异常
 * @createTime 2021年10月18日
 */
public class TencentApiException extends  RuntimeException {

    public TencentApiException(String message){
        super(message);
    }

    public TencentApiException(Throwable cause){
        super(cause);
    }
}
