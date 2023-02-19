package com.dongshuishui.apidriver.service;

import com.dongshuishui.apidriver.remote.ServiceDriverUserClient;
import com.dongshuishui.apidriver.remote.ServiceVerificationcodeClient;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.constant.DriverCarConstants;
import com.dongshuishui.internalcommon.constant.IdentityConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.DriverUserExistsResponse;
import com.dongshuishui.response.NumberCodeReponse;
import com.dongshuishui.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  10:11
 * @Description: com.dongshuishui.apidriver.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public ResponseResult checkAndSendVerificationCode(String driverPhone){
        //查询 service-driver-user，该手机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);

        DriverUserExistsResponse driverUserExistsResponse = driverUserExistsResponseResponseResult.getData();
        Integer isExists = driverUserExistsResponse.getIsExists();
        if (isExists == DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info(driverPhone + "的司机存在");
        //获取验证码
        ResponseResult<NumberCodeReponse> numberCodeResult = serviceVerificationcodeClient.getNumberCode(6);
        NumberCodeReponse numberCodeReponse = numberCodeResult.getData();
        int numberCode = numberCodeReponse.getNumberCode();
        log.info("验证码：" + numberCode);
        //调用第三方发送验证码，第三方：阿里短信服务，腾讯的短信通，华信，容联。
        //todo
        //存入reids中 1、生成key，2存入value
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstants.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key, numberCode + "",2, TimeUnit.MINUTES);
        return ResponseResult.success("");
    }
}
