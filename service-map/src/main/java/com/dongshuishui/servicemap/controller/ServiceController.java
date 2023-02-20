package com.dongshuishui.servicemap.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.service.ServiceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/20  15:43
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceMapService serviceMapService;
    /**
     * 创建服务
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name){

        return serviceMapService.add(name);
    }
}
