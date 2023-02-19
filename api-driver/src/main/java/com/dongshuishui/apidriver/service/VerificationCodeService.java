package com.dongshuishui.apidriver.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  10:11
 * @Description: com.dongshuishui.apidriver.service
 * @Version: 1.0
 */
@Service
public class VerificationCodeService {
    public ResponseResult checkAndSendVerificationCode(String driverPhone){
        //查询 service-driver-user，该手机是否存在

        //获取验证码

        //掉用第三方发送验证码

        //存入reids中

        return ResponseResult.success("");
    }
}
