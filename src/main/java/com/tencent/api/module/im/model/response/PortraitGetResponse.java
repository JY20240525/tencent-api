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
 * @Description 拉取资料
 * @createTime 2025年03月18日
 * 返回参数说明
 * <p>
 * 字段名          类型                     说明
 * -------------------------------------------------------------------------------------
 * UserProfileItem Array                   返回的用户资料结构化信息
 * To_Account      String                   返回的用户的 UserID
 * ProfileItem     Array                    返回的用户的资料对象数组，
 *                                          数组中每一个对象都包含 Tag 和 Value
 * Tag            String                   返回的资料对象的名称：
 *                                          - 标配资料字段（详情可参见 标配资料字段）
 *                                          - 自定义资料字段（详情可参见 自定义资料字段）
 * Value          uint64_t/string/bytes    拉取的资料对象的值，详情可参见 资料字段
 * ResultCode     Integer                  To_Account 的处理结果：
 *                                          - 0 - 成功
 *                                          - 非 0 - 失败
 * ResultInfo     String                   To_Account 的错误描述信息，成功时该字段为空
 * Fail_Account   Array                    返回处理失败的用户列表，仅当存在失败用户时才返回该字段
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PortraitGetResponse extends IMResponse {

    @SerializedName("UserProfileItem")
    private List<UserProfileItem> userProfileItem;
    @Data
    public static class  UserProfileItem implements Serializable{
        @SerializedName("To_Account")
        private String toAccount;
        @SerializedName("ProfileItem")
        private List<ProfileItem> profileItem;
    }

    @Data
    public static class ProfileItem implements Serializable{
        @SerializedName("Tag")
        private String tag;
        @SerializedName("Value")
        private Object value;
    }
}
