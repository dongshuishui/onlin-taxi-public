package com.dongshuishui.apipassenger.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.NumberCodeReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  15:16
 * @Description: com.dongshuishui.apipassenger.remote
 * @Version: 1.0
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationcodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    ResponseResult<NumberCodeReponse> getNumberCode(@PathVariable("size") int size);
}
