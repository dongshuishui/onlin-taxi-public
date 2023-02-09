package com.dongshuishui.internalcommon.constant;

import lombok.Getter;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  12:41
 * @Description: com.dongshuishui.internalcommon.constant
 * @Version: 1.0
 */
public enum CommonStatusEnum {
    //成功
    SUCCESS(1,"success"),
    //失败
    FAIL(1,"fail");
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
