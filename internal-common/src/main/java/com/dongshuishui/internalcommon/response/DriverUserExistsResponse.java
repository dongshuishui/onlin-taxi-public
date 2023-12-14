package com.dongshuishui.internalcommon.response;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  11:17
 * @Description: com.dongshuishui.response
 * @Version: 1.0
 */
@Data
public class DriverUserExistsResponse {
    private String driverPhone;
    private Integer isExists;
}
