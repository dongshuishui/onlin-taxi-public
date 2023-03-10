package com.dongshuishui.apipassenger.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:14
 * @Description: com.dongshuishui.apipassenger.controller
 * @Version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }

    /**
     * 需要token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 不需要token
     * @return
     */
    @GetMapping("noauthTest")
    public ResponseResult noAuthTest(){
        return ResponseResult.success("no auth test");
    }
}
