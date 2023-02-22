package com.dongshuishui.internalcommon.request;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  14:14
 * @Description: com.dongshuishui.request
 * @Version: 1.0
 */
@Data
public class ApiDriverPointRequest {
    public Long carId;

    public PointDTO[] points;
}

