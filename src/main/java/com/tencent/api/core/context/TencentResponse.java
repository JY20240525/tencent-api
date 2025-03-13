package com.tencent.api.core.context;

import lombok.Data;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 腾讯 API 响应
 * @createTime 2022年04月20日
 */
@Data
public class TencentResponse {

    public TencentResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    private int statusCode;
    private String body;
}
