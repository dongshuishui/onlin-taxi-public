package com.dongshuishui.util;

/**
 * @Auther: 东水水
 * @Date: 2023/2/13  14:35
 * @Description: com.dongshuishui.util
 * @Version: 1.0
 */
public class RedisPrefixUtils {

    //乘客验证码的前缀，获取验证码
    public static String verificationCodePrefix = "passenger-verifaiction-code-";

    //token存储的前缀
    public static String tokenPrefix = "token-";
    /**
     * 根据手机号，生成key
     * @param passengerPhone
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone){
        return  verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识生成key
     * @param phone 手机号码
     * @param identity 身份标识 乘客是1，司机是2；
     * @return
     */
    public static String genertorTokenKey(String phone, String identity){
        return tokenPrefix + phone + "-" + identity;
    }

}
