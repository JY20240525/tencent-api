package com.tencent.api.module.im.service.impl;

import cn.hutool.core.lang.Assert;
import com.google.gson.Gson;
import com.tencent.api.TencentApiTemplate;
import com.tencent.api.core.context.TencentResponse;
import com.tencent.api.core.crypto.ParamsEncoder;
import com.tencent.api.module.im.model.IMResponse;
import com.tencent.api.module.im.model.request.*;
import com.tencent.api.module.im.model.response.*;
import com.tencent.api.module.im.service.IMTencentUserService;
import org.apache.http.HttpStatus;

/**
 * @author raolei
 * @version 1.0.0
 * @Description IMTencentUserService IMPL
 * @createTime 2025年03月17日
 */
public class IMTencentUserServiceImpl implements IMTencentUserService {
    private final TencentApiTemplate tencentApiTemplate;
    private final ParamsEncoder paramsEncoder;

    public IMTencentUserServiceImpl(TencentApiTemplate tencentApiTemplate, ParamsEncoder paramsEncoder) {
        this.tencentApiTemplate = tencentApiTemplate;
        this.paramsEncoder = paramsEncoder;
    }

    @Override
    public IMResponse accountImportRequest(AccountImportRequest request) {
        String url = "/v4/im_open_login_svc/account_import?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), ContactGroupAddResponse.class);
    }

    @Override
    public MultiAccountImportResponse multiAccountImport(MultiAccountImportRequest request) {
        String url = "/v4/im_open_login_svc/multiaccount_import?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), MultiAccountImportResponse.class);
    }

    @Override
    public AccountDeleteResponse accountDelete(AccountDeleteRequest request) {
        String url = "/v4/im_open_login_svc/account_delete?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), AccountDeleteResponse.class);
    }

    @Override
    public AccountCheckResponse accountCheck(AccountCheckRequest request) {
        String url = "/v4/im_open_login_svc/account_check?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), AccountCheckResponse.class);
    }

    @Override
    public PortraitSetResponse portraitSet(PortraitSetRequest request) {
        String url = "/v4/profile/portrait_set?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), PortraitSetResponse.class);
    }

    @Override
    public PortraitGetResponse portraitGet(PortraitGetRequest request) {
        String url = "/v4/profile/portrait_get?" + paramsEncoder.encode(request.getParams(), request);
        TencentResponse response = tencentApiTemplate.post(url, request);
        Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, "Tencent Api 调用错误 status: " + response.getStatusCode() + " body: " + response.getBody());
        return new Gson().fromJson(response.getBody(), PortraitGetResponse.class);
    }
}
