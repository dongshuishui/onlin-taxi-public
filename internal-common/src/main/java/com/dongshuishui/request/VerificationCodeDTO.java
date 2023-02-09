package com.dongshuishui.request;

import lombok.Data;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:51
 * @Description: com.dongshuishui.apipassenger.request
 * @Version: 1.0
 */
@Data
public class VerificationCodeDTO {
    private String passengerPhone;

    private String verificationCode;

}
