package com.dongshuishui.serviceorder.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.OrderRequest;
import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 东水水
 * @since 2023-02-22
 */
@Service
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 新增订单
     * @param orderRequest
     * @return
     */
    public ResponseResult add(OrderRequest orderRequest) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);

        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success("");
    }
}
