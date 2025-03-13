package com.tencent.api.utils;

import cn.hutool.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.Deflater;

/**
 * 腾讯tls签名工具类
 *
 * @link https://github.com/tencentyun/tls-sig-api-v2-java/blob/master/src/main/java/com/tencentyun/TLSSigAPIv2.java
 */
public class TTLSSignUtil {

    private static String hmacsha256(long appId, String key, String identifier, long currTime, long expire, String base64UserBuf) {
        String contentToBeSigned = "TLS.identifier:" + identifier + "\n"
                + "TLS.sdkappid:" + appId + "\n"
                + "TLS.time:" + currTime + "\n"
                + "TLS.expire:" + expire + "\n";
        if (null != base64UserBuf) {
            contentToBeSigned += "TLS.userbuf:" + base64UserBuf + "\n";
        }
        try {
            byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, "HmacSHA256");
            hmac.init(keySpec);
            byte[] byteSig = hmac.doFinal(contentToBeSigned.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(byteSig)).replaceAll("\\s*", "");
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return "";
        }
    }

    /**
     * 获取签名
     *
     * @param appId
     * @param key
     * @param identifier 用户id
     * @param expire 过期时间 单位：秒
     * @param userBuf
     * @return
     */
    public static String genSig(long appId, String key, String identifier, long expire, byte[] userBuf) {

        long currTime = System.currentTimeMillis() / 1000;

        JSONObject sigDoc = new JSONObject();
        sigDoc.set("TLS.ver", "2.0");
        sigDoc.set("TLS.identifier", identifier);
        sigDoc.set("TLS.sdkappid", appId);
        sigDoc.set("TLS.expire", expire);
        sigDoc.set("TLS.time", currTime);

        String base64UserBuf = null;
        if (null != userBuf) {
            base64UserBuf = new String(Base64.getEncoder().encode(userBuf));
            sigDoc.put("TLS.userbuf", base64UserBuf);
        }
        String sig = hmacsha256(appId, key, identifier, currTime, expire, base64UserBuf);
        if (sig.length() == 0) {
            return "";
        }
        sigDoc.set("TLS.sig", sig);
        Deflater compressor = new Deflater();
        compressor.setInput(sigDoc.toString().getBytes(StandardCharsets.UTF_8));
        compressor.finish();
        byte [] compressedBytes = new byte[2048];
        int compressedBytesLength = compressor.deflate(compressedBytes);
        compressor.end();
        return (new String(base64EncodeUrl(Arrays.copyOfRange(compressedBytes,
                0, compressedBytesLength)))).replaceAll("\\s*", "");
    }

    private static byte[] base64EncodeUrl(byte[] input) {
        byte[] base64 = Base64.getEncoder().encode(input);
        for (int i = 0; i < base64.length; ++i)
            switch (base64[i]) {
                case '+':
                    base64[i] = '*';
                    break;
                case '/':
                    base64[i] = '-';
                    break;
                case '=':
                    base64[i] = '_';
                    break;
                default:
                    break;
            }
        return base64;
    }
}
