package com.dongshuishui.apidriver.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.NumberCodeReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  14:08
 * @Description: com.dongshuishui.apidriver.remote
 * @Version: 1.0
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationcodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResult<NumberCodeReponse> getNumberCode(@PathVariable("size") int size);

}
