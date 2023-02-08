package com.dongshuishui.apipassenger.controller;

import com.dongshuishui.apipassenger.request.VerificationCodeDTO;
import com.dongshuishui.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:49
 * @Description: com.dongshuishui.apipassenger.controller
 * @Version: 1.0
 */
@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("//verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone =verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机参数：" + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
}
