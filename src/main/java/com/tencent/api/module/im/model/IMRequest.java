package com.tencent.api.module.im.model;

import cn.hutool.core.util.RandomUtil;
import com.tencent.api.core.context.TencentRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IMRequest<T> implements Serializable, TencentRequest<T> {
    private final transient List<NameValuePair> params;

    private IMRequest() {
        this.params = new ArrayList<>();
    }

    public IMRequest(String identifier) {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("identifier", identifier));
        list.add(new BasicNameValuePair("random", String.valueOf(RandomUtil.randomInt(9))));
        list.add(new BasicNameValuePair("contenttype", "json"));
        this.params = list;
    }


    @Override
    public T getData() {
        return null;
    }

    @Override
    public List<NameValuePair> getParams() {
        return params;
    }
}
