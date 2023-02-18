package com.dongshuishui.apiboss.service;

import com.dongshuishui.apiboss.remote.ServiceDriverUserClients;
import com.dongshuishui.internalcommon.dto.DriverCarBindingRelationship;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/18  16:02
 * @Description: com.dongshuishui.apiboss.service
 * @Version: 1.0
 */
@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    private ServiceDriverUserClients serviceDriverUserClients;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClients.bind(driverCarBindingRelationship);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {

        return serviceDriverUserClients.unbind(driverCarBindingRelationship);
    }
}
