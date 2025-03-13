package com.tencent.api.module.im.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

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
