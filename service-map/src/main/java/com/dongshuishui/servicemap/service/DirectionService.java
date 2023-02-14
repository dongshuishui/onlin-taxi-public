package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  17:10
 * @Description: com.dongshuishui.servicemap.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class DirectionService {

    /**
     * 根据起点和终点经纬度来获取距离和时长
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(123);
        directionResponse.setDuration(11);
        return ResponseResult.success(directionResponse);
    }
}
