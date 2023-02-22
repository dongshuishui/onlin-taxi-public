package com.dongshuishui.internalcommon.reponse;

import lombok.Data;

/**
 *
 * @Author: 东水水
 * @Date: 2023/2/14  14:51
 * @Description: com.dongshuishui.response
 * @Version: 1.0
 */
@Data
public class ForecastPriceResponse {
    private double price;

    /**
     * 城市代码
     */
    private String cityCode;
    /**
     * 车辆类型
     */
    private String vehicleType;
}
