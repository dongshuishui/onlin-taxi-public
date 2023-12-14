package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.DirectionResponse;
import com.dongshuishui.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * 根据起点和终点经纬度来获取距离和时长
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //调用第三方地图接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);

        return ResponseResult.success(direction);
    }
}
