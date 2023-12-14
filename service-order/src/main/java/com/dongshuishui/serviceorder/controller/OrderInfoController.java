package com.dongshuishui.serviceorder.controller;


import com.dongshuishui.internalcommon.constant.HeaderParamConstants;
import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.OrderRequest;
import com.dongshuishui.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     * @param orderInfo
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderInfo orderInfo){
        //测试通过，通过head获取deviceCode
//        String deviceCode = httpServletRequest.getHeader(HeaderParamConstants.DEVICE_CODE);
//        orderInfo.setDeviceCode(deviceCode);

        log.info("service-order:" + orderInfo.getAddress() );
        return orderInfoService.add(orderInfo);
    }
}
