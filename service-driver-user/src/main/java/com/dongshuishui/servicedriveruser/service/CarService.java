package com.dongshuishui.servicedriveruser.service;

import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/17  14:12
 * @Description: com.dongshuishui.servicedriveruser.service
 * @Version: 1.0
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car){
        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
