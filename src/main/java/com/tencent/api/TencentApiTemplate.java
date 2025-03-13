package com.tencent.api;

import com.tencent.api.core.context.TencentRequest;
import com.tencent.api.core.context.TencentResponse;
import com.tencent.api.utils.TencentExceptionsHelper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * @author raolei
 * @version 1.0.0
 * @Description Tencent (腾讯) sdk 基础使用 ApiTemplate
 * @createTime 2022年04月19日
 */
public class TencentApiTemplate implements ITencentApiOperations {
    private static final Logger LOG = LoggerFactory.getLogger(TencentApiTemplate.class);
    @NotNull
    private CloseableHttpClient httpClient;
    /**
     * API 请求地址
     */
    @NotNull
    private String home;


    @Override
    public TencentResponse get(String uri) {
        final String url = home + uri;
        LOG.debug("tencent api get url: {}", url);
        return execute(client -> {
            HttpResponse response = client.execute(new HttpGet(url));
            String responseBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine() == null ? HttpStatus.SC_INTERNAL_SERVER_ERROR : response.getStatusLine().getStatusCode();
            if (!Objects.equals(statusCode, HttpStatus.SC_OK)) {
                LOG.error("tencent api get uri: {},status: {},message: {}", url, response.getStatusLine() != null ? response.getStatusLine().getStatusCode() : "未知",
                        StringUtils.isNotEmpty(responseBody) ? responseBody : "返回无内容");
            }
            return new TencentResponse(statusCode, responseBody);
        });
    }

    @Override
    public <T extends TencentRequest<?>> TencentResponse post(String uri, T body) {
        final String url = home + uri;
        String requestBody = new Gson().toJson(body.getData());
        LOG.debug("tencent api post url: {} request body: {}", url, requestBody);
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
        return execute(client -> {
            HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine() == null ? HttpStatus.SC_INTERNAL_SERVER_ERROR : response.getStatusLine().getStatusCode();
            if (!Objects.equals(statusCode, HttpStatus.SC_OK)) {
                LOG.error("tencent api post  uri: {},status: {},response: {}", uri, response.getStatusLine() != null ? response.getStatusLine().getStatusCode() : "未知",
                        StringUtils.isNotEmpty(responseBody) ? responseBody : "返回无内容");
            }
            return new TencentResponse(statusCode, responseBody);
        });
    }

    @Override
    public <T> T execute(ITencentApiCallback<T> callback) {
        try {
            return callback.doInTencent(httpClient);
        } catch (Exception ex) {
            throw TencentExceptionsHelper.convertToTencentApi(ex);
        }
    }


    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
