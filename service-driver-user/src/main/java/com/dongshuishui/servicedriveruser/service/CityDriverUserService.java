package com.dongshuishui.servicedriveruser.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：东水水
 * @createTime：2023/12/13 -0:41
 * @describe: com.dongshuishui.servicedriveruser.service
 */
@Service
public class CityDriverUserService {
    @Autowired
    DriverUserMapper driverUserMapper;

    /**
     * 根据cityCode查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    public ResponseResult<Boolean> isAvailableDriver(String cityCode){
        int i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if (i>0){
            return ResponseResult.success(true);
        }else {
            return ResponseResult.success(false);
        }
    }

}
