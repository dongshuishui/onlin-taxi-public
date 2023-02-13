package com.dongshuishui.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dongshuishui.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 东水水
 * @Date: 2023/2/12  18:59
 * @Description: com.dongshuishui.util
 * @Version: 1.0
 */
public class JwtUtils {
    //盐
    private static final String SIGN = "GPFmsb!@#$$";

    private static final String JWT_KEY_PHONE = "phone";

    //乘客是1，司机是2；
    private static final String JWT_KEY_IDNTITY = "identity";

    //生成token
    public static String generatorToken(String passengerPhone, String identity){
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDNTITY, identity);

        //token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //整合过期时间
        //builder.withExpiresAt(date);  不在这里控制有效时间，在redis中控制

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }
    //解析token
    public static TokenResult paresToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDNTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    public static void main(String[] args) {

        String s = generatorToken("13855141090","1");
        System.out.println("生成的tonken：" + s);

        TokenResult tokenResult = paresToken(s);

        System.out.println("解析token后的值：passengerPhone:" + tokenResult.getPhone() +"，identity：" + tokenResult.getIdentity());
    }
}
