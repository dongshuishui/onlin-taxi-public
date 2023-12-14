package com.dongshuishui.servicedriveruser.controller;

import com.dongshuishui.internalcommon.constant.DriverCarConstants;
import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.DriverUserExistsResponse;
import com.dongshuishui.internalcommon.response.OrderDriverResponse;
import com.dongshuishui.servicedriveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  17:37
 * @Description: com.dongshuishui.servicedriveruser.controller
 * @Version: 1.0
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 新增司机
     * @param driverUser
     * @return
     */
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult getUser(@PathVariable("driverPhone") String driverPhone){

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDB = driverUserByPhone.getData();
        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();

        int isExists = DriverCarConstants.DRIVER_EXISTS;
        if(driverUserDB == null){
            isExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            driverUserExistsResponse.setDriverPhone(driverPhone);
            driverUserExistsResponse.setIsExists(isExists);
        }else {
            driverUserExistsResponse.setDriverPhone(driverUserDB.getDriverPhone());
            driverUserExistsResponse.setIsExists(isExists);
        }
        return ResponseResult.success(driverUserExistsResponse);
    }

    /**
     * 根据车辆ID查询订单需要的司机信息
     * @param carId
     * @return
     */
    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") long carId){
        return driverUserService.getAvailableDriver(carId);
    }

}
