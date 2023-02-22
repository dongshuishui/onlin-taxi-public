package com.dongshuishui.apipassenger.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/22  9:14
 * @Description: com.dongshuishui.apipassenger.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 创建订单/下单
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);
        return null;
    }
}
