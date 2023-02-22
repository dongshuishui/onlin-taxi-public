package com.dongshuishui.apipassenger.service;

import com.dongshuishui.apipassenger.remote.ServicePriceClient;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.ForecastPriceDTO;
import com.dongshuishui.internalcommon.reponse.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 东水水
 * @Date: 2023/2/14  14:45
 * @Description: com.dongshuishui.apipassenger.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    /**
     * 根据 出发地经纬度 和 目的地经纬度 来获取预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude,String cityCode, String vehicleType){

        log.info("出发地经度：" + depLongitude +",出发地纬度：" + depLatitude );
        log.info("目的地经度：" + destLongitude + ",目的地纬度：" + destLatitude);
        log.info("调用计价服务，计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);
        ResponseResult<ForecastPriceResponse> responseResult = servicePriceClient.forecastPrice(forecastPriceDTO);
        double price = responseResult.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        forecastPriceResponse.setCityCode(responseResult.getData().getCityCode());
        forecastPriceResponse.setVehicleType(responseResult.getData().getVehicleType());
        return ResponseResult.success(forecastPriceResponse);
    }
}
