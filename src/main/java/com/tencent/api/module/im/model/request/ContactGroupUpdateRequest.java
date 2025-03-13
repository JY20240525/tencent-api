

package com.tencent.api.module.im.model.request;

import com.tencent.api.module.im.model.ContactItem;
import com.tencent.api.module.im.model.IMRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 更新会话分组数据
 * @createTime 2025年03月07日
 * @see <a href="https://cloud.tencent.com/document/product/269/85793">doc</a>
 * <p>
 * 请求参数说明
 * <p>
 * 字段名              类型       必填       说明
 * ------------------------------------------
 * From_Account       String     是       请求方 uid
 * UpdateType         Integer    是       更新类型：1 - 分组添加或删除会话
 * UpdateGroup        Object     是       分组维度增删会话
 *   ** UpdateGroupType    Integer    是       更新类型： 1 - 更新分组名 2 - 更新会话分组
 *   ** OldGroupName       String     否       待更新的分组名 (UpdateGroupType=1时必填)
 *   ** NewGroupName       String     否       更新后的分组名，最多支持32个字节 (UpdateGroupType=1时必填)
 *   ** ContactUpdateItem  Array      否       指定更新的会话
 *      **  ContactOptType     Integer    是       更新类型：1 - 分组添加会话 2 - 分组删除会话
 *      ** ContactItem          Object     是       会话对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupUpdateRequest extends IMRequest<ContactGroupUpdateRequest> implements Serializable {
    private transient String requestId;
    /**
     * 更新的会话分组的 im 账号
     */
    @SerializedName("From_Account")
    private String fromAccount;
    @SerializedName("UpdateType")
    private Integer updateType = 1;
    @SerializedName("UpdateGroup")
    private UpdateGroup updateGroup;

    /**
     * @param identifier  用户名，调用 REST API 时必须为 App 管理员账号
     * @param fromAccount 更新的会话分组的 im 账号
     * @param updateGroup UpdateGroup.buildUpdateGroupName() 更新分组名称
     *                    UpdateGroup.buildUpdateGroupContact() 更新会话分组
     */
    public ContactGroupUpdateRequest(String identifier, String fromAccount, UpdateGroup updateGroup) {
        super(identifier);
        this.fromAccount = fromAccount;
        this.updateGroup = updateGroup;
        this.requestId = UUID.randomUUID().toString();
    }

    /**
     * 分组添加会话
     */
    public ContactGroupUpdateRequest addContact(String toAccount) {
        this.getUpdateGroup().getContactUpdateItem().add(
                UpdateGroup.ContactUpdateItem.builder().contactOptType(2).contactItem(ContactItem.builder()
                        .type(1).toAccount(toAccount).build()).build());
        return this;
    }

    /**
     * 分组删除会话
     */
    public ContactGroupUpdateRequest deleteContact(String toAccount) {
        this.getUpdateGroup().getContactUpdateItem().add(
                UpdateGroup.ContactUpdateItem.builder().contactOptType(1).contactItem(ContactItem.builder()
                        .type(1).toAccount(toAccount).build()).build());
        return this;
    }

    @Override
    public ContactGroupUpdateRequest getData() {
        return this;
    }

    @Data
    public static class UpdateGroup implements Serializable {

        /**
         * 更新分组名称
         */
        public static UpdateGroup buildUpdateGroupName(String oldGroupName, String newGroupName) {
            UpdateGroup updateGroup = new UpdateGroup();
            updateGroup.setUpdateGroupType(1);
            updateGroup.setOldGroupName(oldGroupName);
            updateGroup.setNewGroupName(newGroupName);
            return updateGroup;
        }

        /**
         * 更新会话分组添加人或者删除人
         */
        public static UpdateGroup buildUpdateGroupContact(String oldGroupName) {
            UpdateGroup updateGroup = new UpdateGroup();
            updateGroup.setUpdateGroupType(2);
            updateGroup.setOldGroupName(oldGroupName);
            return updateGroup;
        }

        @SerializedName("UpdateGroupType")
        private Integer updateGroupType;
        @SerializedName("OldGroupName")
        private String oldGroupName;
        @SerializedName("NewGroupName")
        private String newGroupName;
        @SerializedName("ContactUpdateItem")
        private List<ContactUpdateItem> contactUpdateItem = new ArrayList<>();

        @Data
        @Builder
        public static class ContactUpdateItem implements Serializable {
            @SerializedName("ContactOptType")
            private Integer contactOptType;
            @SerializedName("ContactItem")
            private ContactItem contactItem;
        }
    }
}
