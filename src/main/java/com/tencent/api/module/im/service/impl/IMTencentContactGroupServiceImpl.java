package com.tencent.api.module.im.service.impl;

import cn.hutool.core.lang.Assert;
import com.tencent.api.TencentApiTemplate;

import com.tencent.api.core.context.TencentResponse;
import com.tencent.api.core.crypto.ParamsEncoder;
import com.tencent.api.module.im.model.request.ContactGroupAddRequest;
import com.tencent.api.module.im.model.request.ContactGroupDeleteRequest;
import com.tencent.api.module.im.model.request.ContactGroupGetRequest;
import com.tencent.api.module.im.model.request.ContactGroupUpdateRequest;
import com.tencent.api.module.im.model.response.ContactGroupAddResponse;
import com.tencent.api.module.im.model.response.ContactGroupDeleteResponse;
import com.tencent.api.module.im.model.response.ContactGroupGetResponse;
import com.tencent.api.module.im.model.response.ContactGroupUpdateResponse;
import com.tencent.api.module.im.service.IMTencentContactGroupService;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;

/**
 * @author raolei
 * @version 1.0.0
 * @Description IMTencentContactGroupService Impl
 * @createTime 2025年03月07日
 */
public class IMTencentContactGroupServiceImpl implements IMTencentContactGroupService {

    private final TencentApiTemplate tencentApiTemplate;
    private final ParamsEncoder paramsEncoder;

    public IMTencentContactGroupServiceImpl(TencentApiTemplate tencentApiTemplate, ParamsEncoder paramsEncoder) {
        this.tencentApiTemplate = tencentApiTemplate;
        this.paramsEncoder = paramsEncoder;
    }

    @Override
    public ContactGroupAddResponse createContactGroup(ContactGroupAddRequest request) {
        String url = "/v4/recentcontact/create_contact_group?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), ContactGroupAddResponse.class);
    }

    @Override
    public ContactGroupUpdateResponse updateContactGroup(ContactGroupUpdateRequest request) {
        String url = "/v4/recentcontact/update_contact_group?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), ContactGroupUpdateResponse.class);
    }

    @Override
    public ContactGroupDeleteResponse deleteContactGroup(ContactGroupDeleteRequest request) {
        String url = "/v4/recentcontact/del_contact_group?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), ContactGroupDeleteResponse.class);
    }

    @Override
    public ContactGroupGetResponse getContactGroup(ContactGroupGetRequest request) {
        String url = "/v4/recentcontact/get_contact_group?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), ContactGroupGetResponse.class);
    }
}
