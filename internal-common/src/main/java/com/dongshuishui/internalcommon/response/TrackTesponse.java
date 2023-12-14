package com.dongshuishui.internalcommon.response;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  8:48
 * @Description: com.dongshuishui.response
 * @Version: 1.0
 */
@Data
public class TrackTesponse {
    /**
     * 轨迹的唯一编号
     */
    private String trid;
    /**
     * 轨迹名称
     */
    private String trname;
}
