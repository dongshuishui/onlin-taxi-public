package com.dongshuishui.apipassenger.controller;

import com.dongshuishui.apipassenger.remote.ServiceOrderClient;
import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:14
 * @Description: com.dongshuishui.apipassenger.controller
 * @Version: 1.0
 */
@RestController
public class TestController {
    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }

    /**
     * 需要token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 不需要token
     * @return
     */
    @GetMapping("noauthTest")
    public ResponseResult noAuthTest(){
        return ResponseResult.success("no auth test");
    }

    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        System.out.println("并发测试orderId:" + orderId);

        serviceOrderClient.dispatchRealTimeOrder(orderId);
        return "test-real-time-order success";
    }
}
