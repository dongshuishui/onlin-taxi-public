package com.dongshuishui.serviceorder.controller;


import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.OrderRequest;
import com.dongshuishui.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 东水水
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 新增订单
     * @param orderRequest
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info("service-order:" + orderRequest.getAddress() );
        System.out.println(orderRequest.getAddress());
        return orderInfoService.add(orderRequest);
    }
}
