package com.dongshuishui.internalcommon.dto;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  22:44
 * @Description: com.dongshuishui.internalcommon.dto
 * @Version: 1.0
 */
@Data
public class PriceRule {

    /**
     * 城市代码
     */
    private String  cityCode;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 起步价，单位：元
     */
    private Double startFare;

    /**
     * 起步里程，单位：km
     */
    private Integer startMile;

    /**
     * 计程单价（按公里），单位：元
     */
    private Double unitPricePerMile;

    /**
     * 计程单价（按分钟），单位：元
     */
    private Double unitPricePerMinute;
}
