package com.dongshuishui.apidriver.controller;

import com.dongshuishui.apidriver.service.PointService;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  14:10
 * @Description: com.dongshuishui.apidriver.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){

        return pointService.uploa(apiDriverPointRequest);
    }
}
