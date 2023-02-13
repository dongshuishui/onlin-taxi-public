package com.dongshuishui.apipassenger.service;

import com.dongshuishui.apipassenger.remote.ServicePassengerUserClient;
import com.dongshuishui.apipassenger.remote.ServiceVerificationcodeClient;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.constant.IndentityConstants;
import com.dongshuishui.internalcommon.constant.TokenConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResponse;
import com.dongshuishui.request.VerificationCodeDTO;
import com.dongshuishui.response.NumberCodeReponse;
import com.dongshuishui.util.JwtUtils;
import com.dongshuishui.util.RedisPrefixUtils;
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

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeReponse> numberCodeReponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeReponse.getData().getNumberCode();

        //key,value,过期时间
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        //存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短息服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通，华信，容联。

        //返回值
        return ResponseResult.success("");
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
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);

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

        //判断原来是否有用户，并进行对应的处理，
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO );

        //颁发令牌
        String accessToken = JwtUtils.generatorToken(passengerPhone, IndentityConstants.PASSENGER_INDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IndentityConstants.PASSENGER_INDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);

        //将token放入redis中
        String accessTokenKey = RedisPrefixUtils.genertorTokenKey(passengerPhone, IndentityConstants.PASSENGER_INDENTITY,TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.genertorTokenKey(passengerPhone, IndentityConstants.PASSENGER_INDENTITY,TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
