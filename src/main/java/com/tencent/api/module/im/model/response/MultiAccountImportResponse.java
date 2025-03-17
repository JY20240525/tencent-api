package com.tencent.api.module.im.model.response;

import com.google.gson.annotations.SerializedName;
import com.tencent.api.module.im.model.IMResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 导入多个账号
 * @createTime 2025年03月17日
 * * 返回参数说明
 * *
 * * 字段名        类型      说明
 * * -----------------------------------
 * * FailAccounts  Array    导入失败的账号列表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultiAccountImportResponse extends IMResponse {

    @SerializedName("FailAccounts")
    private List<String> failAccounts;
}
