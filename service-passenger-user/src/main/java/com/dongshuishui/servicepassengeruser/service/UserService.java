package com.dongshuishui.servicepassengeruser.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  20:49
 * @Description: com.dongshuishui.servicepassengeruser.service
 * @Version: 1.0
 */
@Service
public class UserService {


    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("user service 被调用，手机号：" + passengerPhone);

        //根据手机号查询用户信息

        //判断用户信息是否存在

        //如果不存在，插入用户信息
        return ResponseResult.success();
    }
}
