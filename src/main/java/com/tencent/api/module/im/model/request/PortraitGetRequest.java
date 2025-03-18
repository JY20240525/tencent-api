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
 * @Description 拉取资料
 * @createTime 2025年03月18日
 * @see <a href="https://cloud.tencent.com/document/product/269/1639">doc</a>
 * 请求参数说明
 * <p>
 * 字段名       类型    必填   说明
 * ------------------------------------------------------------------------------------
 * To_Account  Array   是   需要拉取这些 UserID 的资料。
 * 注意：每次拉取的用户数不得超过 100，避免因回包数据量太大导致回包失败。
 * TagList     Array   是   指定要拉取的资料字段的 Tag，支持的字段包括：
 * - 标配资料字段（详情可参见 标配资料字段）
 * - 自定义资料字段（详情可参见 自定义资料字段）
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PortraitGetRequest extends IMRequest<PortraitGetRequest> implements Serializable {
    @SerializedName("To_Account")
    private List<String> toAccount = new ArrayList<>();
    @SerializedName("TagList")
    private List<String> tagList = new ArrayList<>();

    public PortraitGetRequest(String identifier) {
        super(identifier);
    }

    public PortraitGetRequest addToAccount(String toAccount) {
        this.toAccount.add(toAccount);
        return this;
    }

    public PortraitGetRequest addTagList(String tag) {
        this.tagList.add(tag);
        return this;
    }

    @Override
    public PortraitGetRequest getData() {
        return this;
    }
}
