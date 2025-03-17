package com.tencent.api.module.im.service;

import com.tencent.api.module.im.model.IMResponse;
import com.tencent.api.module.im.model.request.AccountCheckRequest;
import com.tencent.api.module.im.model.request.AccountDeleteRequest;
import com.tencent.api.module.im.model.request.AccountImportRequest;
import com.tencent.api.module.im.model.request.MultiAccountImportRequest;
import com.tencent.api.module.im.model.response.AccountCheckResponse;
import com.tencent.api.module.im.model.response.AccountDeleteResponse;
import com.tencent.api.module.im.model.response.MultiAccountImportResponse;

/**
 * @author raolei
 * @version 1.0.0
 * @Description 用户管理
 * @createTime 2025年03月17日
 * @see <a href="https://cloud.tencent.com/document/product/269/1608">doc</>
 */
public interface IMTencentUserService {

    /**
     * 导入单个账号
     */
    IMResponse accountImportRequest(AccountImportRequest request);

    /**
     * 导入多个账号
     */
    MultiAccountImportResponse multiAccountImport(MultiAccountImportRequest request);


    /**
     * 删除账号
     */
    AccountDeleteResponse accountDelete(AccountDeleteRequest request);

    /**
     * 查询账号
     */
    AccountCheckResponse accountCheck(AccountCheckRequest request);
}
