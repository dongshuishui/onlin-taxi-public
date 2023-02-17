package com.dongshuishui.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.constant.DriverCarConstants;
import com.dongshuishui.internalcommon.dto.DriverCarBindingRelationship;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 东水水
 * @since 2023-02-17
 */
@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult bing(DriverCarBindingRelationship driverCarBindingRelationship){
        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);

        //判断该参数中的车辆和司机，已经做过绑定，则不允许再次绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",driverCarBindingRelationship.getBindState());
        Integer count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(count.intValue() > 0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getValue());
        }

        //司机被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state",driverCarBindingRelationship.getBindState());
        count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(count.intValue() > 0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_BIND_EXISTS.getCode(),CommonStatusEnum.DRIVER_BIND_EXISTS.getValue());
        }

        //车辆被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",driverCarBindingRelationship.getBindState());
        count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(count.intValue() > 0){
            return ResponseResult.fail(CommonStatusEnum.CAR_BIND_EXISTS.getCode(),CommonStatusEnum.CAR_BIND_EXISTS.getValue());
        }

        LocalDateTime now = LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success("");
    }

    public ResponseResult unBind(DriverCarBindingRelationship driverCarBindingRelationship) {
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> map = new HashMap<>();
        map.put("driver_id",driverCarBindingRelationship.getDriverId());
        map.put("car_id",driverCarBindingRelationship.getCarId());
        map.put("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if(driverCarBindingRelationships.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getValue());
        }

        DriverCarBindingRelationship relationship = driverCarBindingRelationships.get(0);
        relationship.setBindState(DriverCarConstants.DRIVER_CAR_UNBIND);
        relationship.setUnBindingTime(now);
        driverCarBindingRelationshipMapper.updateById(relationship);
        return ResponseResult.success("");
    }
}
