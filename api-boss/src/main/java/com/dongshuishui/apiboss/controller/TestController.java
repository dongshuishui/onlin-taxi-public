package com.dongshuishui.apiboss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  19:45
 * @Description: com.dongshuishui.apiboss.controller
 * @Version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "api-boss";
    }
}
