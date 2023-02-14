package com.dongshuishui.serviceprice.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  15:55
 * @Description: com.dongshuishui.serviceprice.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        log.info("出发地经度：" + depLongitude +",出发地纬度：" + depLatitude );
        log.info("目的地经度：" + destLongitude + ",目的地纬度：" + destLatitude);
        log.info("调用地图服务，查询地图和时长");

        log.info("读取计价规则");

        log.info("根据距离、时长和计价规则，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);

    }
}