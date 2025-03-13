package com.tencent.api.module.im.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回参数说明
 * <p>
 * 字段名        类型      说明
 * -----------------------------------
 * contactItem   Object   会话对象
 * ResultCode   Integer  对应会话的操作结果，错误码，0表示成功
 * ResultInfo   String   对应会话的操作结果，错误描述
 */
@Data
public class ResultItem implements Serializable {
    @SerializedName("contactItem")
    private ContactItem contactItem;
    @SerializedName("ResultCode")
    private Integer resultCode;
    @SerializedName("ResultInfo")
    private String resultInfo;
}
