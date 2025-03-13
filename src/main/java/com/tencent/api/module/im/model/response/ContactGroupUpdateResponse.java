package com.tencent.api.module.im.model.response;

import com.tencent.api.module.im.model.ContactItem;
import com.tencent.api.module.im.model.IMResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 更新会话分组返回数据
 * @createTime 2025年03月07日
 * 返回参数说明
 * <p>
 * 字段名              类型       必填       说明
 * ------------------------------------------
 * UpdateType         Integer    是       更新类型： - 分组添加或删除会话
 * UpdateGroupResult  Object     否       分组维度更新结果
 *   ** UpdateGroupType    Integer    是       更新类型：1 - 更新分组名 2 - 更新会话分组
 *   ** GroupName          String     否       当前操作的分组名称
 *   ** OldGroupName       String     否       当前操作老的分组名称
 *   ** GroupId            Integer    否       当前操作的分组 ID
 * ContactResultItem  Array      否       会话操作结果
 *   ** ContactOptType     Integer    是       更新类型：1 - 分组添加会话 2 - 分组删除会话
 *   ** ContactItem        Object     选填  会话对象
 *   ** ResultCode         Integer    否    对应会话的操作结果，错误码，0表示成功
 *   ** ResultInfo         String     否    对应会话的操作结果，错误描述
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupUpdateResponse extends IMResponse implements Serializable {
    @SerializedName("UpdateType")
    private Integer updateType;

    @SerializedName("UpdateGroupResult")
    private UpdateGroupResult updateGroupResult;

    @Data
    public static class UpdateGroupResult implements Serializable {
        @SerializedName("UpdateGroupType")
        private Integer updateGroupType;
        @SerializedName("GroupName")
        private String groupName;
        @SerializedName("GroupId")
        private Long groupId;
        @SerializedName("OldGroupName")
        private String oldGroupName;
        @SerializedName("ContactResultItem")
        private List<ContactResultItem> contactResultItem;
    }

    @Data
    public static class ContactResultItem implements Serializable {
        @SerializedName("ContactOptType")
        private Integer contactOptType;
        @SerializedName("ContactItem")
        private ContactItem contactItem;
        @SerializedName("ResultCode")
        private Integer resultCode;
        @SerializedName("ResultInfo")
        private String resultInfo;
    }
}
