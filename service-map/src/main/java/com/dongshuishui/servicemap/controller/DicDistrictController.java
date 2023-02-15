package com.dongshuishui.servicemap.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/15  19:31
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
@RestController
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDistrict(String keywords){
        return dicDistrictService.initDicDistrict(keywords);
    }
}
