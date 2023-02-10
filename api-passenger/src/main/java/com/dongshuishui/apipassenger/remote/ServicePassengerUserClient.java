package com.dongshuishui.apipassenger.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: 东水水
 * @Date: 2023/2/10  17:51
 * @Description: com.dongshuishui.apipassenger.remote
 * @Version: 1.0
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
