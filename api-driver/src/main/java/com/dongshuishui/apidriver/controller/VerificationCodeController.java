package com.dongshuishui.apidriver.controller;

import com.dongshuishui.apidriver.service.VerificationCodeService;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  9:58
 * @Description: com.dongshuishui.apidriver.controller
 * @Version: 1.0
 */
@RestController
@Slf4j
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        log.info("司机的号码：" + driverPhone);
        return verificationCodeService.checkAndSendVerificationCode(driverPhone);
    }
}
