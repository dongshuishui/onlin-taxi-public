package com.dongshuishui.apidriver.service;

import com.dongshuishui.apidriver.remote.ServiceDriverUserClient;
import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  22:44
 * @Description: com.dongshuishui.apidriver.service
 * @Version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    public ResponseResult udpateUser(DriverUser driverUser){
        return serviceDriverUserClient.updateUser(driverUser);
    }
}
