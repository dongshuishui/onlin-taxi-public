package com.dongshuishui.internalcommon.constant;

/**
 * 订单常量类
 * @Author: 东水水
 * @Date: 2023/2/22  14:18
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
public class OrderConstants {
    /**
     * 订单状态 0：订单无效
     */
    public static final int ORDER_INVALID = 0;

    /**
     * 订单状态 1：订单开始
     */
    public static final int ORDER_START = 1;

    /**
     * 订单状态 2：司机接单
     */
    public static final int DRIVER_RECEIVE_ORDER = 2;

    /**
     * 订单状态 3: 去接乘客
     */
    public static final int DRIVER_TO_PICK_UP_PASSENGER = 3;

    /**
     * 订单状态 4：司机到达乘客起点
     */
    public static final int DRIVER_ARRIVED_DEPARTURE = 4;

    /**
     * 订单状态  5：乘客上车，司机开始行程
     */
    public static final int PICK_UP_PASSENGER = 5;

    /**
     * 订单状态 6: 到达目的地，行程结束，未支付
     */
    public static final int PASSENGER_GETOFF = 6;

    /**
     * 订单状态 7：发起收款
     */
    public static final int TO_START_PAY = 7;

    /**
     * 订单状态  8: 支付完成
     */
    public static final int SUCCESS_PAY = 8;

    /**
     *  订单状态 9.订单取消
     */
    public static final int ORDER_CANCEL = 9;


}
