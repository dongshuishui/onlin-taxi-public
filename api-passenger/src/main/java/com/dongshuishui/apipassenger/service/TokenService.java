package com.dongshuishui.apipassenger.service;

import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.constant.TokenConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResponse;
import com.dongshuishui.internalcommon.dto.TokenResult;
import com.dongshuishui.util.JwtUtils;
import com.dongshuishui.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 东水水
 * @Date: 2023/2/13  16:35
 * @Description: com.dongshuishui.apipassenger.service
 * @Version: 1.0
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){
        //解析refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identty = tokenResult.getIdentity();

        //读取redis中的refreshToken
        String refreshTokenKey = RedisPrefixUtils.genertorTokenKey(phone, identty, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        //校验refreshToken
        if((StringUtils.isBlank(refreshTokenRedis) )|| (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        //生成双token
        String refreshToken = JwtUtils.generatorToken(phone, identty, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identty, TokenConstants.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.genertorTokenKey(phone, identty, TokenConstants.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
