package com.tencent.api;


import org.apache.http.client.HttpClient;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 请求回调
 * @createTime 2022年04月19日
 */
@FunctionalInterface
public interface ITencentApiCallback<T> {
    /**
     * 发起请求
     */
    T doInTencent(HttpClient client) throws Exception;
}
