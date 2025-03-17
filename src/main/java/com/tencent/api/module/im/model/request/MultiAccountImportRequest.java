package com.tencent.api.module.im.model.request;

import com.google.gson.annotations.SerializedName;
import com.tencent.api.module.im.model.IMRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 导入多个账号
 * @createTime 2025年03月17日
 * @see <a href="https://cloud.tencent.com/document/product/269/4919">doc</a>
 * 请求参数说明
 * <p>
 * 字段名       类型    必填   说明
 * --------------------------------------
 * AccountList  Array   是   待导入的用户列表，单次最多导入 100 个用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultiAccountImportRequest extends IMRequest<MultiAccountImportRequest> implements Serializable {
    @SerializedName("AccountList")
    private List<AccountImportRequest> accountList;

    /**
     * @param identifier 用户名，调用 REST API 时必须为 App 管理员账号
     */
    public MultiAccountImportRequest(String identifier) {
        super(identifier);
    }

    @Override
    public MultiAccountImportRequest getData() {
        return this;
    }
}
