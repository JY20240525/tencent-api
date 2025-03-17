package com.tencent.api.module.im.model.request;

import com.google.gson.annotations.SerializedName;
import com.tencent.api.module.im.model.IMRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 删除账号
 * @createTime 2025年03月17日
 * @see <a href="https://cloud.tencent.com/document/product/269/36443">doc</a>
 * 请求参数说明
 * <p>
 * 字段名       类型    必填   说明
 * -----------------------------------------------
 * DeleteItem   Array   是   请求删除的账号对象数组，单次请求最多支持 100 个账号
 * UserID       String  是   请求删除的账号的 UserID
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDeleteRequest extends IMRequest<AccountDeleteRequest> implements Serializable {
    @SerializedName("DeleteItem")
    private List<DeleteItem> deleteItem = new ArrayList<>();


    /**
     * @param identifier 用户名，调用 REST API 时必须为 App 管理员账号
     */
    public AccountDeleteRequest(String identifier) {
        super(identifier);
    }

    public AccountDeleteRequest addDeleteItem(String userId) {
        this.deleteItem.add(new DeleteItem(userId));
        return this;
    }

    @Override
    public AccountDeleteRequest getData() {
        return this;
    }

    @Data
    public static class DeleteItem implements Serializable {
        public DeleteItem(String userId) {
            this.userId = userId;

        }

        @SerializedName("UserID")
        private String userId;
    }

}
