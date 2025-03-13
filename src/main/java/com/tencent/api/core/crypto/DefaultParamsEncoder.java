package com.tencent.api.core.crypto;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.CharsetUtil;

import cn.hutool.crypto.digest.DigestUtil;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.validation.constraints.NotEmpty;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author raolei
 * @version 1.0.0
 * @Description ParamsEncoder impl
 * @createTime 2022年04月20日
 */
public class DefaultParamsEncoder implements ParamsEncoder {
    /**
     * 要注意保密，如有泄漏应尽快重新重成。
     */
    @NotEmpty
    private final String secretKey;

    public DefaultParamsEncoder(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String encode(List<NameValuePair> params) {
        String q = params.stream().sorted(Comparator.comparing(NameValuePair::getName))
                .map(n -> n.getName() + "=" + URLEncodeUtil.encode(n.getValue(), CharsetUtil.CHARSET_UTF_8)).collect(Collectors.joining("&"));
        return q + "&sig=" + DigestUtil.md5Hex(URLDecoder.decode(q, CharsetUtil.CHARSET_UTF_8) + secretKey);
    }

    @Override
    public <T> String encode(List<NameValuePair> params, T data) {
        String dataMd5 = DigestUtil.md5Hex(new Gson().toJson(data));
        params.add(new BasicNameValuePair("data", dataMd5));
        String q = params.stream().sorted(Comparator.comparing(NameValuePair::getName))
                .map(n -> n.getName() + "=" + n.getValue()).collect(Collectors.joining("&"));
        return "sig=" + DigestUtil.md5Hex(q + secretKey);
    }
}
