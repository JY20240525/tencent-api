package com.tencent.api.core.crypto;

import com.tencent.api.utils.TTLSSignUtil;
import org.apache.http.NameValuePair;

import javax.validation.constraints.NotEmpty;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IMParamsEncoder implements ParamsEncoder {

    /**
     * 腾讯给使用方的身份证
     */
    @NotEmpty
    private final String sdkAppId;

    /**
     * 要注意保密，如有泄漏应尽快重新重成。
     */
    @NotEmpty
    private final String secretKey;

    public IMParamsEncoder(String sdkAppId, String secretKey) {
        this.sdkAppId = sdkAppId;
        this.secretKey = secretKey;
    }

    @Override
    public String encode(List<NameValuePair> params) {
        // 未实现 用到的时候再走
        return "";
    }

    @Override
    public <T> String encode(List<NameValuePair> params, T body) {

        // APP管理员
        String identifier = params.stream().filter(t -> Objects.equals(t.getName(), "identifier")).collect(Collectors.toList()).get(0).getValue();
        // 签名 过期 24 小时
        String userSin = TTLSSignUtil.genSig(Long.parseLong(sdkAppId), secretKey, identifier, 24 * 60 * 60, null);

        String q = params.stream().sorted(Comparator.comparing(NameValuePair::getName))
                .map(n -> n.getName() + "=" + n.getValue()).collect(Collectors.joining("&"));

        return q + "&usersig=" + userSin + "&sdkappid=" + sdkAppId;
    }
}
