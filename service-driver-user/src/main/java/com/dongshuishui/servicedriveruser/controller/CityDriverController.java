package com.dongshuishui.servicedriveruser.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：东水水
 * @createTime：2023/12/13 -0:45
 * @describe: com.dongshuishui.servicedriveruser.controller
 */
@RestController
@RequestMapping("/city-dirver")
public class CityDriverController {
    @Autowired
    CityDriverUserService cityDriverUserService;

    /**
     * 根据cityCode查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    @RequestMapping("/is-available-driver")
    public ResponseResult isAvailableDriver(String cityCode){
        return  cityDriverUserService.isAvailableDriver(cityCode);
    }
}
