package com.dongshuishui.servicedriveruser.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  15:48
 * @Description: com.dongshuishui.servicedriveruser.controller
 * @Version: 1.0
 */
@RestController
public class TestController {
    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public String test(){
        return "service-driver-user";
    }

    @GetMapping("/test-db")
    public ResponseResult testDb(){
        return driverUserService.testGetDriverUser();
    }
}
