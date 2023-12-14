package com.dongshuishui.internalcommon.response;

import lombok.Data;

/**
 * @author：东水水
 * @createTime：2023/12/13 -21:31
 * @describe: com.dongshuishui.internalcommon.reponse
 */
@Data
public class OrderDriverResponse {
    private Long driverId;
    private String driverPhone;
    private Long carId;

    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车辆号牌
     */
    private String vehicleNo;
}
