package com.dongshuishui.internalcommon.constant;

import lombok.Getter;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  12:41
 * @Description: com.dongshuishui.internalcommon.constant
 * @Version: 1.0
 */
public enum CommonStatusEnum {

    //验证码错误提示：1000-1099
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    /**
     * Token类提示 1100 - 1199
     */
    TOKEN_ERROR(1199, "token错误"),

    /**
     * 用户提示：1200-1299
     */
    USER_NOT_EXISTS(1299,"当前用户不存在"),

    /**
     * 计价规则：1300-1399
     */
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

    PRICE_RULE_EXISTS(1301,"计价规制已存在，不允许添加"),

    PRICE_RULE_NOT_EDIT(1302,"计价规制没有变化"),
    /**
     * 地图信息：1400-1499
     */
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),

    /**
     * 司机和车辆：1500-1599
     */
    DRIVER_CAR_BIND_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"),

    DRIVER_NOT_EXISTS(1501,"司机不存在"),

    DRIVER_CAR_BIND_EXISTS(1502,"司机和车辆绑定关系已存在，请勿重复绑定"),

    DRIVER_BIND_EXISTS(1503,"司机被绑定了，请勿重复绑定"),

    CAR_BIND_EXISTS(1503,"车辆被绑定了，请勿重复绑定"),

    CITY_DRIVER_EMPTY(1505,"当前城市没有可用的司机"),

    AVAILABLE_DRIVER_EMPTY(1506, "可用的司机为空"),
    /**
     * 订单： 1600 - 169
     */
    ORDER_NOT_CREATE(1600, "有正在进行的订单"),

    DEVICE_IS_BLACK(1601,"该设备超过下单次数"),

    //成功
    SUCCESS(1,"success"),

    //失败
    FAIL(1,"fail");
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
