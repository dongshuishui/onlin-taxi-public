package com.dongshuishui.internalcommon.dto;

import lombok.Data;

/**
 * @Author: 东水水
 * @Date: 2023/2/15  17:38
 * @Description: com.dongshuishui.internalcommon.dto
 * @Version: 1.0
 */
@Data
public class DicDistrict {

    /**
     * 地区代码
     */
    private String addressCode;

    /**
     * 地区名称
     */
    private String addressName;

    /**
     * 父地区代码
     */
    private String parentAddressCode;

    /**
     * 级别
     */
    private Integer level;
}
