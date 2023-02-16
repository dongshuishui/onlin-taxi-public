package com.dongshuishui.apiboss.remote;

import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  19:59
 * @Description: com.dongshuishui.apiboss.remote
 * @Version: 1.0
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClients {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);
}
