package com.dongshuishui.apipassenger.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 调用service-order服务
 * @Author: 东水水
 * @Date: 2023/2/22  10:30
 * @Description: com.dongshuishui.apipassenger.remote
 * @Version: 1.0
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    /**
     * 调用service-order服务的/order/add接口<br/>
     * 新增订单接口
     * @param orderRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    ResponseResult add(@RequestBody OrderRequest orderRequest);
}
