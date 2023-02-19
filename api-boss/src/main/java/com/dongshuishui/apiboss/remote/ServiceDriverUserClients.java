package com.dongshuishui.apiboss.remote;

import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.DriverCarBindingRelationship;
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
    ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    ResponseResult addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/bind")
    ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/unbind")
    ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}
