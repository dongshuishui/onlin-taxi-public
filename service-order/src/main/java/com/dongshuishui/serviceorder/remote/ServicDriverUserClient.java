package com.dongshuishui.serviceorder.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author：东水水
 * @createTime：2023/12/13 -10:30
 * @describe: com.dongshuishui.serviceorder.remote
 */
@FeignClient("service-driver-user")
public interface ServicDriverUserClient {

    @GetMapping("/city-dirver/is-available-driver")
    public ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);
    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") long carId);
}
