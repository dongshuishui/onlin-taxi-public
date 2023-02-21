package com.dongshuishui.servicedriveruser.service;

import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.TerminalResponse;
import com.dongshuishui.response.TrackTesponse;
import com.dongshuishui.servicedriveruser.mapper.CarMapper;
import com.dongshuishui.servicedriveruser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        String name = car.getVehicleNo();
        //获取此车辆对应的终端id：tid
        ResponseResult<TerminalResponse> terminalResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = terminalResult.getData().getTid();
        car.setTid(tid);

        //获的此车辆的轨迹id：trid
        ResponseResult<TrackTesponse> trackTesponseResponseResult = serviceMapClient.addTrack(tid);
        String trid = trackTesponseResponseResult.getData().getTrid();
        String trname = trackTesponseResponseResult.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);

        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
