package com.dongshuishui.servicemap.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  8:40
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @PostMapping("/add")
    public ResponseResult add(String tid){
        return trackService.add(tid);
    }
}
