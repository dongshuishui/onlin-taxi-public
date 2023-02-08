package com.dongshuishui.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  1:59
 * @Description: com.dongshuishui.serviceverificationcode.controller
 * @Version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return  "service-verificationcode";
    }
}
