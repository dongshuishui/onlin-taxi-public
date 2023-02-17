package com.dongshuishui.servicedriveruser.controller;


import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 东水水
 * @since 2023-02-17
 */
@RestController
public class CarController {

    @Autowired
    private CarService carService;
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){

        return carService.addCar(car);
    }
}
