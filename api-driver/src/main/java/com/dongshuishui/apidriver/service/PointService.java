package com.dongshuishui.apidriver.service;

import com.dongshuishui.apidriver.remote.ServiceDriverUserClient;
import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  14:16
 * @Description: com.dongshuishui.apidriver.service
 * @Version: 1.0
 */
@Service
public class PointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult uploa(ApiDriverPointRequest apiDriverPointRequest){
        //获取carId
        Long carId = apiDriverPointRequest.getCarId();
        //通过carId获取tid，trid  调用service-driver-user的接口
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();

        //调用地图上传

        return ResponseResult.success("");
    }
}
