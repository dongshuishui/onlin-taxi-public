package com.dongshuishui.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  16:39
 * @Description: com.dongshuishui.apidriver.controller
 * @Version: 1.0
 */
@RestController
public class TestController {

    /**
     * 需要授权的接口
     * @return
     */
    @GetMapping("/auth")
    public String testAuth(){
        return "auth";
    }

    /**
     * 不需要授权的接口
     * @return
     */
    @GetMapping("/noauth")
    public String testNoAuth(){
        return "no auth";
    }
}
