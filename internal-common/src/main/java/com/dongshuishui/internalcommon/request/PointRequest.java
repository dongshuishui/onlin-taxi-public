package com.dongshuishui.internalcommon.request;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  11:00
 * @Description: com.dongshuishui.request
 * @Version: 1.0
 */
@Data
public class PointRequest {
    /**
     * 设备唯一编号
     */
    private String tid;

    /**
     * 轨迹ID
     */
    private String trid;
    /**
     * 具体上传点的信息
     */
    private PointDTO[] points;
}
