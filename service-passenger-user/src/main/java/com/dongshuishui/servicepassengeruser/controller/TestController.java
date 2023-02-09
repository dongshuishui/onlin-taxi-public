package com.dongshuishui.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  20:16
 * @Description: com.dongshuishui.servicepassengeruser.controller
 * @Version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("")
    public String test(){
        return "service-passenger-user";
    }
}
