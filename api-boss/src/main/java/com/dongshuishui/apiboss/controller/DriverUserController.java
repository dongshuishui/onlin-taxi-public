package com.dongshuishui.apiboss.controller;

import com.dongshuishui.apiboss.service.CarService;
import com.dongshuishui.apiboss.service.DriverUserService;
import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  19:55
 * @Description: com.dongshuishui.apiboss.controller
 * @Version: 1.0
 */
@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private CarService carService;

    /**
     * 添加司机
     * @param driverUser
     * @return
     */
    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机
     * @param driverUser
     * @return
     */
    @PutMapping("/driver-user")
    public ResponseResult updateDriver(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }

    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return carService.addCar(car);
    }



}
