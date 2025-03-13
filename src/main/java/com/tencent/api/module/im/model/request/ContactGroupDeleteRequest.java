package com.tencent.api.module.im.model.request;

import com.tencent.api.module.im.model.IMRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 删除会话分组数据
 * @createTime 2025年03月07日
 * @see <a href="https://cloud.tencent.com/document/product/269/85795">doc</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupDeleteRequest extends IMRequest<ContactGroupDeleteRequest> implements Serializable {
    private transient String requestId;

    /**
     * 更新的会话分组的 im 账号
     */
    @SerializedName("From_Account")
    private String fromAccount;
    @SerializedName("GroupName")
    private Set<String> groupNames = new HashSet<>();

    /**
     *
     * @param identifier  用户名，调用 REST API 时必须为 App 管理员账号
     * @param fromAccount 更新的会话分组的 im 账号
     */
    public ContactGroupDeleteRequest(String identifier, String fromAccount) {
        super(identifier);
        this.fromAccount = fromAccount;
        this.requestId = UUID.randomUUID().toString();
    }

    public ContactGroupDeleteRequest add(String... groupNames) {
        this.groupNames.addAll(Arrays.asList(groupNames));
        return this;
    }

    @Override
    public ContactGroupDeleteRequest getData() {
        return this;
    }
}
