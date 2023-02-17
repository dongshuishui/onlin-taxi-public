package com.dongshuishui.apiboss.service;

import com.dongshuishui.apiboss.remote.ServiceDriverUserClients;
import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

/**
 * @Author: 东水水
 * @Date: 2023/2/17  17:35
 * @Description: com.dongshuishui.apiboss.service
 * @Version: 1.0
 */
@Service
public class CarService {
    @Autowired
    private ServiceDriverUserClients serviceDriverUserClients;

    public ResponseResult addCar(Car car){
        return serviceDriverUserClients.addCar(car);
    }

    public ResponseResult udpateCar(Car car) {
        return null;
    }
}
