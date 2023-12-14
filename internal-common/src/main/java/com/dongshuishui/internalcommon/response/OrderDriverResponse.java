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
}
