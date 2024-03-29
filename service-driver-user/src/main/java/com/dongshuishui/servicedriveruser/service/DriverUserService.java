package com.dongshuishui.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.constant.DriverCarConstants;
import com.dongshuishui.internalcommon.dto.Car;
import com.dongshuishui.internalcommon.dto.DriverCarBindingRelationship;
import com.dongshuishui.internalcommon.dto.DriverUser;
import com.dongshuishui.internalcommon.dto.DriverUserWorkStatus;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.OrderDriverResponse;
import com.dongshuishui.servicedriveruser.mapper.CarMapper;
import com.dongshuishui.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.dongshuishui.servicedriveruser.mapper.DriverUserMapper;
import com.dongshuishui.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  16:16
 * @Description: com.dongshuishui.servicedriveruser.service
 * @Version: 1.0
 */
@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Autowired
    private CarMapper carMapper;
    public ResponseResult testGetDriverUser(){
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }

    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.insert(driverUser);
        //初始化  司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return ResponseResult.success("");
    }

    public ResponseResult updateDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success("");
    }

    public ResponseResult<DriverUser> getDriverUserByPhone(String  driverPhone){
        Map<String, Object> map = new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if(driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }

    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") long carid){
        // 车辆跟绑定关系的查询
        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("car_id",carid);
        driverCarBindingRelationshipQueryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(driverCarBindingRelationshipQueryWrapper);

        Long driverId = driverCarBindingRelationship.getDriverId();

        // 司机工作状态查询
        QueryWrapper<DriverUserWorkStatus> driverUserWorkStatusQueryWrapper = new QueryWrapper<>();
        driverUserWorkStatusQueryWrapper.eq("driver_id", driverId);
        driverUserWorkStatusQueryWrapper.eq("work_status",DriverCarConstants.DRIVER_WORK_STATUS_START);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(driverUserWorkStatusQueryWrapper);
        if (null == driverUserWorkStatus ){
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(),CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getValue());
        }else {
            //查询司机信息
            QueryWrapper<DriverUser> driverUserQueryWrapper2 = new QueryWrapper<>();
            driverUserQueryWrapper2.eq("id",driverId);
            DriverUser driverUser = driverUserMapper.selectOne(driverUserQueryWrapper2);
            // 查询车辆信息
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("id",carid);
            Car car = carMapper.selectOne(carQueryWrapper);

            OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
            orderDriverResponse.setCarId(carid);
            orderDriverResponse.setDriverId(driverId);
            orderDriverResponse.setDriverPhone(driverUser.getDriverPhone());
            orderDriverResponse.setVehicleNo(car.getVehicleNo());
            orderDriverResponse.setLicenseId(driverUser.getLicenseId());
            return ResponseResult.success(orderDriverResponse);
        }

    }
}
