package com.dongshuishui.internalcommon.dto;

import lombok.Data;

/**
 * @Auther: 东水水
 * @Date: 2023/2/12  19:35
 * @Description: com.dongshuishui.internalcommon.dto
 * @Version: 1.0
 */
@Data
public class TokenResult {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份标识 乘客是1，司机是2
     */
    private String identity;
}
