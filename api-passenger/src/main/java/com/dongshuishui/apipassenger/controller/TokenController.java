package com.dongshuishui.apipassenger.controller;

import com.dongshuishui.apipassenger.service.TokenService;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/13  16:33
 * @Description: com.dongshuishui.apipassenger.controller
 * @Version: 1.0
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken" + refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }
}
