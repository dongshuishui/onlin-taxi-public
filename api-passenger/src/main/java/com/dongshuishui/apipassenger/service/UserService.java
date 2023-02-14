package com.dongshuishui.apipassenger.service;

import com.dongshuishui.apipassenger.remote.ServicePassengerUserClient;
import com.dongshuishui.internalcommon.dto.PassengerUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResult;
import com.dongshuishui.request.VerificationCodeDTO;
import com.dongshuishui.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 东水水
 * @Date: 2023/2/13  19:39
 * @Description: com.dongshuishui.apipassenger.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserAccessToken(String accessToken){
        log.info("accessToken:" + accessToken);
        //解析accessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号：" + phone);

        //根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }
}
