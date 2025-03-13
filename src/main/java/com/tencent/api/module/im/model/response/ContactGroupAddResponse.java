package com.tencent.api.module.im.model.response;

import com.tencent.api.module.im.model.IMResponse;
import com.tencent.api.module.im.model.GroupItem;
import com.tencent.api.module.im.model.ResultItem;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 会话分组标记
 * @createTime 2025年03月07日
 * 返回参数说明
 * 字段名          类型       说明
 * ---------------------------------
 * GroupResultItem Array 会话分组添加结果
 * ** resultItems  Array    会话分组添加结果
 * ** groupItem    Object   会话分组对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupAddResponse extends IMResponse implements Serializable {
    @SerializedName("GroupResultItem")
    private List<GroupResultItem> groupResultItems;

    @Data
    public static class GroupResultItem implements Serializable {
        @SerializedName("ResultItem")
        private List<ResultItem> resultItems;
        @SerializedName("GroupItem")
        private GroupItem groupItem;
    }
}
