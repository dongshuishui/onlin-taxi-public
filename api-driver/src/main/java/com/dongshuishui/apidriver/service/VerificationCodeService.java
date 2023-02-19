package com.dongshuishui.apidriver.service;

import com.dongshuishui.apidriver.remote.ServiceDriverUserClient;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.constant.DriverCarConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.DriverUserExistsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  10:11
 * @Description: com.dongshuishui.apidriver.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult checkAndSendVerificationCode(String driverPhone){
        //查询 service-driver-user，该手机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);

        DriverUserExistsResponse driverUserExistsResponse = driverUserExistsResponseResponseResult.getData();
        Integer isExists = driverUserExistsResponse.getIsExists();
        if (isExists == DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info(driverPhone + "的司机存在");
        //获取验证码

        //掉用第三方发送验证码

        //存入reids中

        return ResponseResult.success("");
    }
}
