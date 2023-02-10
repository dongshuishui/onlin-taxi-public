package com.dongshuishui.serviceverificationcode.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.NumberCodeReponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  11:36
 * @Description: com.dongshuishui.serviceverificationcode.controller
 * @Version: 1.0
 */
@RestController
public class NumberCodeController {
    @GetMapping("numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size:" + size);
        //生成验证码的操作
        //获取随机数
        double random = (Math.random()*9 + 1)*(Math.pow(10,size-1));
        System.out.println(random);
        int resultInt = (int)random;
        System.out.println("generatr src code:" + resultInt);

        NumberCodeReponse reponse =new NumberCodeReponse();
        reponse.setNumberCode(resultInt);

        return ResponseResult.success(reponse);
    }

}
