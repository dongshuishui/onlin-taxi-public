package com.dongshuishui.servicemap.controller;

import com.dongshuishui.internalcommon.dto.DicDistrict;
import com.dongshuishui.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  16:24
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service map";
    }

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    @GetMapping("/test-map")
    public String testMapper(){
        Map<String, Object> map = new HashMap<>();
        map.put("address_code","110000");
        List<DicDistrict> disDistricts = dicDistrictMapper.selectByMap(map);
        System.out.println(disDistricts);
        return "test-map";
    }

}
