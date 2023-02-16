package com.dongshuishui.apiboss.service;

import com.dongshuishui.apiboss.remote.ServiceDriverUserClients;
import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  19:56
 * @Description: com.dongshuishui.apiboss.service
 * @Version: 1.0
 */
@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClients serviceDriverUserClients;

    public ResponseResult addDriverUser(DriverUser driverUser){
        return serviceDriverUserClients.addDriverUser(driverUser);
    }

    public ResponseResult updateDriverUser(DriverUser driverUser){
        return serviceDriverUserClients.updateDriverUser(driverUser);
    }
}
