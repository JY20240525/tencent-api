package com.tencent.api.module.im.model.request;

import com.google.gson.annotations.SerializedName;
import com.tencent.api.module.im.model.IMRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 导入单个账号
 * @createTime 2025年03月17日
 * @see <a href="https://cloud.tencent.com/document/product/269/1608">doc</a>
 * 请求参数说明
 * <p>
 * 字段名    类型    必填   说明
 * -----------------------------------
 * UserID   String  是   用户名，长度不超过 32 字节
 * Nick     String  否   用户昵称
 * FaceUrl  String  否   用户头像 URL
 */
@Data
public class AccountImportRequest extends IMRequest<AccountImportRequest> implements Serializable {
    @SerializedName("UserID")
    private String userId;
    @SerializedName("Nick")
    private String nick;
    @SerializedName("FaceUrl")
    private String faceUrl;

    @Override
    public AccountImportRequest getData() {
        return this;
    }
}
