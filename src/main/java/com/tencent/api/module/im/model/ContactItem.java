package com.tencent.api.module.im.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 创建会话分组数据 Item
 * @createTime 2025年03月07日
 * 请求参数说明
 * 字段名              类型       必填      说明
 * --------------------------------------
 * type             Integer   是       会话类型：1 - c2c 2 - group
 * toGroupId        String    否       群ID
 * toAccount       String    否       用户ID
 * Timestamp      Integer   会话分组对应最近更新时间戳
 * StandardMark   String    标准标记能力位，bit 位字符串，例如：011111110
 * CustomMark     String    自定义标记
 * ContactGroupId Array     会话所属分组
 */
@Data
@Builder
public class ContactItem implements Serializable {
    @SerializedName("Type")
    private Integer type;
    @SerializedName("ToGroupId")
    private String toGroupId;
    @SerializedName("To_Account")
    private String toAccount;
    @SerializedName("Timestamp")
    private Integer timestamp;
    @SerializedName("StandardMark")
    private String standardMark;
    @SerializedName("CustomMark")
    private String customMark;
    @SerializedName("ContactGroupId")
    private List<Integer> contactGroupId;

}
