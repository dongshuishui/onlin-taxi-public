package com.dongshuishui.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:55
 * @Description: com.dongshuishui.apipassenger.service
 * @Version: 1.0
 */
@Service
public class VerificationCodeService {
    public String generatorCode(String passengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "111111";
        //存入redis
        System.out.println("存入redis");
        //返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message1","success");

        return result.toString();
    }
}
