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
 * @Description 查询账号
 * @createTime 2025年03月17日
 * 返回参数说明
 * <p>
 * 字段名         类型      说明
 * ---------------------------------------------------
 * ResultItem     Array    单个账号的检查结果对象数组
 * UserID         String   请求检查的账号的 UserID
 * ResultCode     Integer  单个账号的检查结果： 0 - 成功 非 0 - 失败
 * ResultInfo     String   单个账号检查失败时的错误描述信息
 * AccountStatus  String   单个账号的导入状态：Imported - 已导入 NotImported - 未导入
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountCheckResponse extends IMResponse implements Serializable {
    @SerializedName("ResultItem")
    private List<ResultItem> resultItem;


    @Data
    public static class ResultItem implements Serializable {
        @SerializedName("UserID")
        private String userId;
        @SerializedName("ResultCode")
        private Integer resultCode;
        @SerializedName("ResultInfo")
        private String resultInfo;
        @SerializedName("AccountStatus")
        private String accountStatus;
    }
}
