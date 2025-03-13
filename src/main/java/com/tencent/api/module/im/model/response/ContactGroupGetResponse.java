package com.tencent.api.module.im.model.response;

import com.tencent.api.module.im.model.ContactItem;
import com.tencent.api.module.im.model.GroupItem;
import com.tencent.api.module.im.model.IMResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 拉取会话分组标记返回数据
 * @createTime 2025年03月07日
 * 返回参数说明
 * 字段名          类型       说明
 * -------------------------------------------
 * ContactItem     Array     会话分组标记数据列表
 * GroupItem      Array     会话分组对象
 * CompleteFlag   Integer   拉取结束标志
 * NextStartIndex Integer   下一次拉取时起始序号
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupGetResponse extends IMResponse implements Serializable {
    @SerializedName("ContactItem")
    private List<ContactItem> contactItem;

    @SerializedName("GroupItem")
    private List<GroupItem> groupItem;

    @SerializedName("CompleteFlag")
    private Integer completeFlag;

    @SerializedName("NextStartIndex")
    private Integer nextStartIndex;

}
