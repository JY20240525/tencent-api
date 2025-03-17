package com.tencent.api.module.im.service;

import com.tencent.api.module.im.model.IMResponse;
import com.tencent.api.module.im.model.request.AccountImportRequest;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 用户管理
 * @createTime 2025年03月17日
 * @see <a href="https://cloud.tencent.com/document/product/269/1608">doc</>
 */
public interface IMTencentUserService {

    IMResponse accountImportRequest(AccountImportRequest request);
}
