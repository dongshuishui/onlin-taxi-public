package com.dongshuishui.apipassenger.service;

import com.dongshuishui.apipassenger.remote.ServiceVerificationcodeClient;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResponse;
import com.dongshuishui.response.NumberCodeReponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
        String key = generatorKeyByPhone(passengerPhone);
        //存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短息服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通，华信，容联。

        //返回值
        return ResponseResult.success("");
    }

    /**
     * 根据手机号，生成key
     * @param passengerPhone
     * @return
     */
    private String generatorKeyByPhone(String passengerPhone){
        return  verificationCodePrefix + passengerPhone;
    }

    /**
     * 校验验证码
     * @param passengerPhone 乘客手机号
     * @param verificationCode 验证码
     * @return
     */

    public ResponseResult checkVerificationCode(String passengerPhone, String verificationCode){
        //根据手机号，到redis读取验证码
        System.out.println("根据手机号，到redis读取验证码");

        //生成key
        String key = generatorKeyByPhone(passengerPhone);

        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value：" + codeRedis);

        //校验验证码
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //判断原来是否有用户，并进行对应的处理
        System.out.println("判断原来是否有用户，并进行对应的处理");

        //颁发令牌
        System.out.println("颁发令牌");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
