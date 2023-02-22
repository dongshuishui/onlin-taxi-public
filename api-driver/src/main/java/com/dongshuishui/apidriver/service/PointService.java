package com.dongshuishui.apidriver.service;

import com.dongshuishui.apidriver.remote.ServiceDriverUserClient;
import com.dongshuishui.apidriver.remote.ServiceMapClient;
import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.ApiDriverPointRequest;
import com.dongshuishui.internalcommon.request.PointDTO;
import com.dongshuishui.internalcommon.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  14:16
 * @Description: com.dongshuishui.apidriver.service
 * @Version: 1.0
 */
@Service
public class PointService {

    @Autowired
    private ServiceMapClient serviceMapClient;
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest){
        //获取carId
        Long carId = apiDriverPointRequest.getCarId();
        //通过carId获取tid，trid  调用service-driver-user的接口
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();

        //调用地图上传
        PointRequest request = new PointRequest();
        PointDTO[] points = apiDriverPointRequest.getPoints();
        request.setTid(tid);
        request.setTrid(trid);
        request.setPoints(points);

        return serviceMapClient.upload(request);
    }
}
