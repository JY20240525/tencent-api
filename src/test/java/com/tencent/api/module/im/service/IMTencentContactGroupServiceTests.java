package com.tencent.api.module.im.service;

import cn.hutool.json.JSONUtil;
import com.tencent.api.TencentApiTemplate;
import com.tencent.api.core.crypto.IMParamsEncoder;
import com.tencent.api.module.im.model.IMResponse;
import com.tencent.api.module.im.model.request.ContactGroupAddRequest;
import com.tencent.api.module.im.model.request.ContactGroupDeleteRequest;
import com.tencent.api.module.im.model.request.ContactGroupGetRequest;
import com.tencent.api.module.im.model.request.ContactGroupUpdateRequest;
import com.tencent.api.module.im.service.impl.IMTencentContactGroupServiceImpl;
import org.apache.http.impl.client.HttpClients;

/**
 * @author raolei
 * @version 1.0.0
 * @Description IMTencentContactGroupService test
 * @createTime 2025年03月13日
 */
public class IMTencentContactGroupServiceTests {
    private static final String HOME = "https://console.tim.qq.com/";

    /**
     * 腾讯给使用方的身份证
     */
    private static final String SDK_APP_ID = "腾讯提供";

    /**
     * 要注意保密，如有泄漏应尽快重新重成。
     */
    private static final String SECRET_KEY = "腾讯提空秘钥";

    /**
     * 签名生成器
     */
    public static IMParamsEncoder imParamsEncoder() {
        return new IMParamsEncoder(SDK_APP_ID, SECRET_KEY);
    }

    /**
     * 腾讯接口 client
     */
    public static TencentApiTemplate tencentApiTemplate() {
        TencentApiTemplate tencentApiTemplate = new TencentApiTemplate();
        tencentApiTemplate.setHome(HOME);
        tencentApiTemplate.setHttpClient(HttpClients.createDefault());
        return tencentApiTemplate;
    }

    /**
     * IM 会话分组接口
     */
    public static IMTencentContactGroupService imTencentContactGroupService() {
        return new IMTencentContactGroupServiceImpl(tencentApiTemplate(), imParamsEncoder());
    }

    /**
     * 添加新的分组
     */
    public static void addGroup() {
        IMTencentContactGroupService imTencentContactGroupService = imTencentContactGroupService();
        ContactGroupAddRequest requestAdd = new ContactGroupAddRequest("administrator", "3d8c2e5601824710972252cefc49e491");
        requestAdd.add("testGroup", "5e15c3ad32204a918faef67d3c3ce75a");
        IMResponse response = imTencentContactGroupService.createContactGroup(requestAdd);
        System.out.println("- ------------------------------------------------------------------------");
        System.out.println("- 返回结果：");
        System.out.println("- " + JSONUtil.toJsonStr(response));
        System.out.println("- ------------------------------------------------------------------------");

    }

    /**
     * 修改分组
     */
    public static void updateGroup() {
        IMTencentContactGroupService imTencentContactGroupService = imTencentContactGroupService();
        ContactGroupUpdateRequest requestUpdate = new ContactGroupUpdateRequest("administrator", "3d8c2e5601824710972252cefc49e491",
                ContactGroupUpdateRequest.UpdateGroup.buildUpdateGroupContact("testGroup"));
        requestUpdate.addContact("5e15c3ad32204a918faef67d3c3ce75a");
        IMResponse response = imTencentContactGroupService.updateContactGroup(requestUpdate);
        System.out.println("- ------------------------------------------------------------------------");
        System.out.println("- 返回结果：");
        System.out.println("- " + JSONUtil.toJsonStr(response));
        System.out.println("- ------------------------------------------------------------------------");
    }

    /**
     * 获取分组
     */
    public static void getGroup() {
        IMTencentContactGroupService imTencentContactGroupService = imTencentContactGroupService();
        ContactGroupGetRequest request = new ContactGroupGetRequest("administrator", "3d8c2e5601824710972252cefc49e491");
        request.setStartIndex(0);
        IMResponse response = imTencentContactGroupService.getContactGroup(request);
        System.out.println("- ------------------------------------------------------------------------");
        System.out.println("- 返回结果：");
        System.out.println("- " + JSONUtil.toJsonStr(response));
        System.out.println("- ------------------------------------------------------------------------");
    }

    /**
     * 删除分组
     */
    public static void deleteGroup() {
        IMTencentContactGroupService imTencentContactGroupService = imTencentContactGroupService();
        ContactGroupDeleteRequest request = new ContactGroupDeleteRequest("administrator", "3d8c2e5601824710972252cefc49e491");
        request.add("testGroup");
        IMResponse response = imTencentContactGroupService.deleteContactGroup(request);
        System.out.println("- ------------------------------------------------------------------------");
        System.out.println("- 返回结果：");
        System.out.println("- " + JSONUtil.toJsonStr(response));
        System.out.println("- ------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        deleteGroup();
    }
}
