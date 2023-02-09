package com.dongshuishui.apipassenger.service;

import com.dongshuishui.apipassenger.remote.ServiceVerificationcodeClient;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.NumberCodeReponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:55
 * @Description: com.dongshuishui.apipassenger.service
 * @Version: 1.0
 */
@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    //乘客验证码的前缀，获取验证码
    private String verificationCodePrefix = "passenger-verifaiction-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeReponse> numberCodeReponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeReponse.getData().getNumberCode();

        //key,value,过期时间
        String key = verificationCodePrefix + passengerPhone;
        //存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短息服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通，华信，容联。

        //返回值
        return ResponseResult.success("");
    }
}
