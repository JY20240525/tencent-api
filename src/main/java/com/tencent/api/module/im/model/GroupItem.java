package com.tencent.api.module.im.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;


/**
 * 返回参数说明
 * <p>
 * 字段名      类型      说明
 * ----------------------------
 * GroupId    Integer   会话分组 ID
 * ResultItem Array     操作结果
 */
@Data
public class GroupItem implements Serializable {
    @SerializedName("GroupName")
    private String groupName;
    @SerializedName("GroupId")
    private Long groupId;
}
