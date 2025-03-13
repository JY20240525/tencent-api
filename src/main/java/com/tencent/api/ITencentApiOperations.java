package com.tencent.api;


import com.tencent.api.core.context.TencentRequest;
import com.tencent.api.core.context.TencentResponse;


/**
 * @author raolei
 * @version 1.0.0
 * @Description Tencent（腾讯） api 使用
 * @createTime 2022年04月19日
 */
public interface ITencentApiOperations {


    <T> T execute(ITencentApiCallback<T> callback);

    TencentResponse get(String uri);

    <T extends TencentRequest<?>> TencentResponse post(String uri, T body);

}
