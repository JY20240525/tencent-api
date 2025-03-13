package com.tencent.api.module.im.model.request;

import com.tencent.api.module.im.model.IMRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 拉取会话分组标记数据
 * @createTime 2025年03月07日
 * @see <a href="https://cloud.tencent.com/document/product/269/85794">doc</a>
 * 请求参数说明
 * 字段名       类型      必填       说明
 * ---------------------------------------
 * From_Account String   是       请求方 uid
 * StartIndex   Integer  是       当前分页拉取的起始索引，首次拉取填 0，后续填上次返回的 NextStartIndex
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContactGroupGetRequest extends IMRequest<ContactGroupGetRequest> implements Serializable {
    private transient String requestId;
    /**
     * 更新的会话分组的 im 账号
     */
    @SerializedName("From_Account")
    private String fromAccount;
    @SerializedName("StartIndex")
    private Integer startIndex;

    /**
     *
     * @param identifier  用户名，调用 REST API 时必须为 App 管理员账号
     * @param fromAccount 更新的会话分组的 im 账号
     */
    public ContactGroupGetRequest(String identifier, String fromAccount) {
        super(identifier);
        this.fromAccount = fromAccount;
        this.requestId = UUID.randomUUID().toString();
    }

    @Override
    public ContactGroupGetRequest getData() {
        return this;
    }
}
