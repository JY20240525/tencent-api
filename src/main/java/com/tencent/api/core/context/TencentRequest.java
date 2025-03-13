package com.tencent.api.core.context;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description Tencent (腾讯) POST 请求
 * @createTime 2022年04月20日
 */
public interface TencentRequest<T> {

    T getData();

    /**
     * 参与签名的其他的参数
     */
    List<NameValuePair> getParams();
}
