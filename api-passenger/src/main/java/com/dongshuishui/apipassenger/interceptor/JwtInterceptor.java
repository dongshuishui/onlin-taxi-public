package com.dongshuishui.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.dto.TokenResult;
import com.dongshuishui.util.JwtUtils;
import net.sf.json.JSONObject;
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;

        String resultString = "";

        String token = request.getHeader("Authorization");

        try {
            TokenResult tokenResult = JwtUtils.paresToken(token);
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
        if(!result){
            PrintWriter out = response.getWriter();
            out.println(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
