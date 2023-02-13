package com.dongshuishui.apipassenger.controller;

import com.dongshuishui.apipassenger.service.UserService;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 东水水
 * @Date: 2023/2/13  19:37
 * @Description: com.dongshuishui.apipassenger.controller
 * @Version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        //从http请求中，获取accesstoken
        String accessToken = request.getHeader("Authorization");


        return userService.getUserAccessToken(accessToken);

    }
}
