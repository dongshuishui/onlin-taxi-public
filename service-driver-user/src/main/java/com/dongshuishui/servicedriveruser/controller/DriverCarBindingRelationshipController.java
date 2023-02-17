package com.dongshuishui.servicedriveruser.controller;


import com.dongshuishui.internalcommon.dto.DriverCarBindingRelationship;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 东水水
 * @since 2023-02-17
 */
@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return  driverCarBindingRelationshipService.bing(driverCarBindingRelationship);
    }

    @PostMapping("/unbind")
    public ResponseResult unBind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.unBind(driverCarBindingRelationship);
    }


}
