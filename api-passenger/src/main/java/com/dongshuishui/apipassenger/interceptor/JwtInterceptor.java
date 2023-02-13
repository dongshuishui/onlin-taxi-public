package com.dongshuishui.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dongshuishui.internalcommon.constant.TokenConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResult;
import com.dongshuishui.util.JwtUtils;
import com.dongshuishui.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: 东水水
 * @Date: 2023/2/12  21:16
 * @Description: com.dongshuishui.apipassenger.interceptor
 * @Version: 1.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;

        String resultString = "";

        String token = request.getHeader("Authorization");

        //解析token
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.paresToken(token);
        }catch (SignatureVerificationException exception){//验证异常
            resultString = "token sign error";
            result = false;
        }catch (TokenExpiredException exception){//token 过期
            resultString = "token time out";
            result = false;
        }catch (AlgorithmMismatchException exception){//算法异常
            resultString = "token AlgorithmMismatchException";
            result = false;
        }catch (Exception exception){
            resultString = "token invalid";
            result = false;
        }
        if(token == null){
            resultString = "token invalid";
            result = false;
        }else {
            //拼接key
            String phone = tokenResult.getPhone();
            String indentity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.genertorTokenKey(phone,indentity, TokenConstants.ACCESS_TOKEN_TYPE);
            //从redis中取出token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if(StringUtils.isBlank(tokenRedis)){
                resultString = "token invalid";
                result = false;
            }else {
                if(!token.trim().equals(tokenRedis.trim())){
                    resultString = "token invalid";
                    result = false;
                }
            }
        }


        //比较传入的token和reids中的token是否相同

        if(!result){
            PrintWriter out = response.getWriter();
            out.println(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
