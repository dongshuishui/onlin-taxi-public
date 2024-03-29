package com.dongshuishui.serviceorder.controller;

import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.serviceorder.mapper.OrderInfoMapper;
import com.dongshuishui.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/22  10:04
 * @Description: com.dongshuishui.serviceorder.controller
 * @Version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-order test";
    }

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderInfoService orderInfoService;

    @Value("${server.port}")
    String port;
    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        System.out.println("server-order 端口: "+port  +", 并发测试orderId:" + orderId);


        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfoService.dispatchRealTiemOrder(orderInfo);
        return "test-real-time-order success";
    }
}
