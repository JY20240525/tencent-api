package com.tencent.api.module.im.model.response;

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
 * @Description 删除会话分组返回数据
 * @createTime 2025年03月07日
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupDeleteResponse extends IMResponse implements Serializable {
    @SerializedName("GroupItem")
    private List<GroupItem> groupItem;
}
