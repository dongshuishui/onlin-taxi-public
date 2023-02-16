package com.dongshuishui.internalcommon.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  15:51
 * @Description: com.dongshuishui.internalcommon.dto
 * @Version: 1.0
 */
@Data
public class DriverUser {
    /**
     * 主键
     */
    private Long id;
    /**
     * 驾驶员注册地行政代码
     */
    private String address; 
    /**
     * 驾驶员姓名
     */
    private String driverName; 
    /**
     * 驾驶员手机号码
     */
    private String driverPhone; 
    /**
     * 驾驶员性别。1男，2女
     */
    private Integer driverGender; 
    /**
     * 驾驶员出生日期
     */
    private LocalDate driverBirthday;
    /**
     * 驾驶员民族
     */
    private String driverNation; 
    /**
     * 驾驶员通信地址
     */
    private String driverContactAddress; 
    /**
     * 机动车驾驶证号
     */
    private String licenseId; 
    /**
     * 初次领取机动车驾驶证日期
     */
    private LocalDate getDriverLicenseDate;
    /**
     * 机动车驾驶证有效期限起
     */
    private LocalDate driverLicenseOn;
    /**
     * 机动车驾驶证有效期限止
     */
    private LocalDate driverLicenseOff;
    /**
     * 是否巡游出租车驾驶员，1：是，0：否
     */
    private Integer taxiDriver; 
    /**
     * 网络预约出租汽车驾驶员资格证号
     */
    private String certificateNo; 
    /**
     * 网络预约出租汽车驾驶员证发证机构
     */
    private String networkCarIssueOrganization; 
    /**
     * 网络预约出租汽车驾驶员资格证发证日期
     */
    private LocalDate networkCarIssueDate;
    /**
     * 初次领取网络预约出租汽车驾驶员资格证日期
     */
    private LocalDate getNetworkCarProofDate;
    /**
     * 网络预约出租汽车驾驶员资格证有效起始日期
     */
    private LocalDate networkCarProofOn;
    /**
     *网络预约出租汽车驾驶员资格证有效截止日期
     */
    private LocalDate networkCarProofOff;
    /**
     * 报备日期
     */
    private LocalDate registerDate;
    /**
     * 服务类型，1：网络预约出租汽车，2：巡游出租汽车，3：私人小客车合乘
     */
    private Integer commercialType; 
    /**
     * 驾驶员合同（或协议）签署公司
     */
    private String contractCompany; 
    /**
     * 驾驶员合同（或协议）有效期起
     */
    private LocalDate contractOn;
    /**
     * 驾驶员合同（或协议）有效期止
     */
    private LocalDate contractOff;
    /**
     * 驾驶员状态。0：有效，1：失效
     */
    private Integer state;



    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


}
