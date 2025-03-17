package com.tencent.api.module.im.model.response;

import com.google.gson.annotations.SerializedName;
import com.tencent.api.module.im.model.IMResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 删除账号
 * @createTime 2025年03月17日
 * 返回参数说明
 * <p>
 * 字段名        类型      说明
 * -------------------------------------------
 * ResultItem    Array    单个账号的结果对象数组
 * ** ResultCode    Integer  单个账号的错误码：0 - 成功 非 0 - 失败
 * ** ResultInfo    String   单个账号删除失败时的错误描述信息
 * ** UserID        String   请求删除的账号的 UserID
 */
@Data
@EqualsAndHashCode(callSuper = true)

public class AccountDeleteResponse extends IMResponse {
    @SerializedName("ResultItem")
    private List<ResultItem> resultItem;

    @Data
    public static class ResultItem implements Serializable {
        @SerializedName("ResultCode")
        private Integer resultCode;
        @SerializedName("ResultInfo")
        private String resultInfo;
        @SerializedName("UserID")
        private String userID;
    }
}
