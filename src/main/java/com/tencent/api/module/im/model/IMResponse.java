package com.tencent.api.module.im.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 返回参数说明
 * <p>
 * 字段名        类型      说明
 * -------------------------------------------
 * ActionStatus  String   请求的处理结果：OK - 处理成功 FAIL - 失败
 * ErrorCode     Integer  错误码：0 - 成功 非 0 - 失败
 * ErrorInfo     String   请求处理失败时的错误信息
 */
@Data
public class IMResponse {

    public static final String OK = "OK";

    @SerializedName("ActionStatus")
    private String actionStatus;
    @SerializedName("ErrorCode")
    private Integer errorCode;
    @SerializedName("ErrorInfo")
    private String errorInfo;
    @SerializedName("ErrorDisplay")
    private String errorDisplay;

}
