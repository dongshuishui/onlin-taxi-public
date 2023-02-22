package com.dongshuishui.internalcommon.request;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  14:38
 * @Description: com.dongshuishui.request
 * @Version: 1.0
 */

@Data
public class ForecastPriceDTO {

    /**
     * 出发地经度
     */
    private String depLongitude;
    /**
     * 出发地纬度
     */
    private String depLatitude;

    /**
     * 目的地经度
     */
    private String destLongitude;

    /**
     * 目的地纬度
     */
    private String destLatitude;
}
