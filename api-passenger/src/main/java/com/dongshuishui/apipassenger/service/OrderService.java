package com.dongshuishui.apipassenger.service;

import com.dongshuishui.apipassenger.remote.ServiceOrderClient;
import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单相关service类
 * @Author: 东水水
 * @Date: 2023/2/22  10:32
 * @Description: com.dongshuishui.apipassenger.service
 * @Version: 1.0
 */
@Service
public class OrderService {
    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    public ResponseResult add(OrderInfo orderInfo){
        return serviceOrderClient.add(orderInfo);
    };
}
