
package com.tencent.api.core.crypto;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Service interface for encoding params encoder.
 * <p>
 * The preferred implementation is {@code DefaultParamsEncoder}.
 *
 * @author raolei
 */
public interface ParamsEncoder {

    /**
     * get 类型签名
     *
     * @param params 参数签名参数
     * @return 返回签名字符
     */
    String encode(List<NameValuePair> params);

    /**
     * post 类型的签名
     *
     * @param params query
     * @param data   body
     * @return 返回签名字符
     */
    <T> String encode(List<NameValuePair> params, T body);
}
