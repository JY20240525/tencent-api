package com.tencent.api.module.im.model.request;

import com.tencent.api.module.im.model.ContactItem;
import com.tencent.api.module.im.model.IMRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 创建会话分组数据
 * @createTime 2025年03月07日
 * @see <a href="https://cloud.tencent.com/document/product/269/85791">doc</a>
 * 请求参数说明
 * 字段名              类型       必填      说明
 * --------------------------------------
 * fromAccount      String    是       请求方uid
 * groupContactItem  Array     是       待添加的会话分组，当前仅支持单个添加
 * ** groupName        String    是       待添加的会话分组名称，最多32个字节
 * ** contactItem      Array     是       待添加的会话列表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupAddRequest extends IMRequest<ContactGroupAddRequest> implements Serializable {

    private transient String requestId;

    /**
     * 更新的会话分组的 im 账号
     */
    @SerializedName("From_Account")
    private String fromAccount;
    @SerializedName("GroupContactItem")
    private List<GroupContactItem> groupContactItem = new ArrayList<>();

    /**
     *
     * @param identifier  用户名，调用 REST API 时必须为 App 管理员账号
     * @param fromAccount 更新的会话分组的 im 账号
     */
    public ContactGroupAddRequest(String identifier, String fromAccount) {
        super(identifier);
        this.fromAccount = fromAccount;
        this.requestId = UUID.randomUUID().toString();

    }

    public ContactGroupAddRequest add(String groupName, String... toAccounts) {
        GroupContactItem gci = this.getGroupContactItem().stream().filter(n -> Objects.equals(n.getGroupName(), groupName)).findFirst().orElse(null);
        if (gci == null) {
            gci = new GroupContactItem();
            gci.setGroupName(groupName);
            this.getGroupContactItem().add(gci);
        }
        if (Objects.equals(gci.getGroupName(), groupName)) {
            for (String toAccount : toAccounts) {
                boolean exist = gci.getContactItem().stream().anyMatch(n -> Objects.equals(n.getToAccount(), toAccount));
                if (!exist) {
                    gci.getContactItem().add(ContactItem.builder().toAccount(toAccount).build());
                }
            }
            return this;
        }
        return this;
    }

    @Data
    public static class GroupContactItem implements Serializable {
        @SerializedName("GroupName")
        private String groupName;
        @SerializedName("ContactItem")
        private List<ContactItem> contactItem = new ArrayList<>();
    }

    @Override
    public ContactGroupAddRequest getData() {
        return this;
    }
}
