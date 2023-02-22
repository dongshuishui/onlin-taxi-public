package com.dongshuishui.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 东水水
 * @since 2023-02-22
 */
@Data
public class PriceRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市代码
     */
    private String cityCode;

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

    /**
     * 运价类型编码
     */
    private String fareType;

    /**
     * 版本
     */
    private Integer fareVersion;
}
