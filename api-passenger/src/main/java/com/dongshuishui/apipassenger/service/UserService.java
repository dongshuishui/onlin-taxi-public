package com.dongshuishui.apipassenger.service;

import com.dongshuishui.internalcommon.dto.PassengerUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResult;
import com.dongshuishui.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
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

    public ResponseResult getUserAccessToken(String accessToken){
        log.info("accessToken:" + accessToken);
        //解析accessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号：" + phone);

        //根据手机号查询用户信息


        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("东水水");
        passengerUser.setProfilePhoto("头像");
        return ResponseResult.success(passengerUser);
    }
}
