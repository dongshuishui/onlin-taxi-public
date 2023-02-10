package com.dongshuishui.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: 东水水
 * @Date: 2023/2/10  16:08
 * @Description: com.dongshuishui.servicepassengeruser.dto
 * @Version: 1.0
 */
@Data
public class PassengerUser {
    private long id;//乘客主键

    private LocalDateTime gmtCreate;//创建时间

    private LocalDateTime gmtModified;//修改时间

    private String passengerPhone;//乘客手机号码

    private String passengerName;//乘客姓名

    private byte passengerGender;//乘客性别 0女，1男

    private byte state;//乘客状态 0有效，1失效


}
