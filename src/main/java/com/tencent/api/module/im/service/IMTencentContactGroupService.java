package com.tencent.api.module.im.service;

import com.tencent.api.module.im.model.request.ContactGroupAddRequest;
import com.tencent.api.module.im.model.request.ContactGroupDeleteRequest;
import com.tencent.api.module.im.model.request.ContactGroupGetRequest;
import com.tencent.api.module.im.model.request.ContactGroupUpdateRequest;
import com.tencent.api.module.im.model.response.ContactGroupAddResponse;
import com.tencent.api.module.im.model.response.ContactGroupDeleteResponse;
import com.tencent.api.module.im.model.response.ContactGroupGetResponse;
import com.tencent.api.module.im.model.response.ContactGroupUpdateResponse;

/**
 * @author raolei
 * @version 1.0.0
 * @Description IM 会话分组
 * @createTime 2025年03月06日
 * @see <a href="https://cloud.tencent.com/document/product/269/85791">doc</a>
 */
public interface IMTencentContactGroupService {

    /**
     * 添加会话分组
     */
    ContactGroupAddResponse createContactGroup(ContactGroupAddRequest request);

    /**
     * 更新会话分组 可以对分组增加会话
     */
    ContactGroupUpdateResponse updateContactGroup(ContactGroupUpdateRequest request);

    /**
     * 删除分组会话
     */
    ContactGroupDeleteResponse deleteContactGroup(ContactGroupDeleteRequest request);

    /**
     * 拉取会话分组标记
     */
    ContactGroupGetResponse getContactGroup(ContactGroupGetRequest request);


}
