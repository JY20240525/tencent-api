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
 * @Description 设置资料
 * @createTime 2025年03月18日
 * @see <a href="https://cloud.tencent.com/document/product/269/1640">aoc</>
 * 请求参数说明
 * <p>
 * 字段名        类型                     必填   说明
 * -------------------------------------------------------------------------
 * From_Account  String                   是   需要设置该 UserID 的资料
 * ProfileItem   Array                    是   待设置的用户的资料对象数组，
 * 数组中每一个对象都包含 Tag 和 Value
 * Tag           String                   是   指定要设置的资料字段的名称，支持设置的资料字段包括：
 * - 标配资料字段（详情可参见 标配资料字段）
 * - 自定义资料字段（详情可参见 自定义资料字段）
 * Value         uint64_t/string/bytes    是   待设置的资料字段的值，详情可参见 <a href="">资料字段</>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PortraitSetRequest extends IMRequest<PortraitSetRequest> implements Serializable {

    @SerializedName("From_Account")
    private String fromAccount;
    @SerializedName("ProfileItem")
    private List<ProfileItem> profileItem = new ArrayList<>();

    public PortraitSetRequest(String identifier, String fromAccount) {
        super(identifier);
        this.fromAccount = fromAccount;
    }

    public PortraitSetRequest addProfileItem(String tag, Object value) {
        this.profileItem.add(new ProfileItem(tag, value));
        return this;
    }

    @Override
    public PortraitSetRequest getData() {
        return this;
    }

    @Data
    public static class ProfileItem implements Serializable {
        public ProfileItem(String tag, Object value) {
            this.tag = tag;
            this.value = value;
        }

        @SerializedName("Tag")
        private String tag;
        @SerializedName("Value")
        private Object value;
    }
}
