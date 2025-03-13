
package com.tencent.api.utils;

import com.tencent.api.TencentApiException;

public class TencentExceptionsHelper {

    public static TencentApiException convertToTencentApi(Exception e) {
        return new TencentApiException(e);
    }

    public static TencentApiException convertToTencentApi(String message) {
        return new TencentApiException(message);
    }
}
