package com.dongshuishui.servicedriveruser.service;

import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  16:16
 * @Description: com.dongshuishui.servicedriveruser.service
 * @Version: 1.0
 */
@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult testGetDriverUser(){
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }
}
