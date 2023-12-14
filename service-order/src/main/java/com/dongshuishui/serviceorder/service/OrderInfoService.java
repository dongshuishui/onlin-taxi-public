package com.dongshuishui.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.PriceRuleIsNewRequest;
import com.dongshuishui.internalcommon.response.OrderDriverResponse;
import com.dongshuishui.internalcommon.response.TerminalResponse;
import com.dongshuishui.internalcommon.constant.OrderConstants;
import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.internalcommon.util.RedisPrefixUtils;
import com.dongshuishui.serviceorder.mapper.OrderInfoMapper;
import com.dongshuishui.serviceorder.remote.ServicDriverUserClient;
import com.dongshuishui.serviceorder.remote.ServiceMapClient;
import com.dongshuishui.serviceorder.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 东水水
 * @since 2023-02-22
 */
@Service
@Slf4j
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    ServicDriverUserClient servicDriverUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private ServicePriceClient servicePriceClient;
    /**
     * 新增订单
     * @param orderInfo
     * @return
     */
    public ResponseResult add(OrderInfo orderInfo) {
        //测试当前城市是否有可用司机
        ResponseResult<Boolean> availableDriver = servicDriverUserClient.isAvailableDriver(orderInfo.getAddress());
        log.info("测试城市是否有司机结果："+availableDriver.getData());
        if(!availableDriver.getData()){
            return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(),CommonStatusEnum.CITY_DRIVER_EMPTY.getValue());
        }

        // 需要判断计价规制是否为最新
        PriceRuleIsNewRequest priceRuleIsNewRequest = new PriceRuleIsNewRequest();
        priceRuleIsNewRequest.setFareType(orderInfo.getFareType());
        priceRuleIsNewRequest.setFareVersion(orderInfo.getFareVersion());
        ResponseResult<Boolean> aNew = servicePriceClient.isNew(priceRuleIsNewRequest);
        if(!(aNew.getData())){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }

        //判断下单的设备是否位黑名单设备
        String deviceCode = orderInfo.getDeviceCode();
        //生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefx + deviceCode;
        //设置key，看看原来有没有
        if (isBlackDevice(deviceCodeKey)) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }
        //判断乘客有正在进行的订单不允许下单
        if(isPassengerOrderGoingon(orderInfo.getPassengerId()) > 0){
            return ResponseResult.fail(CommonStatusEnum.ORDER_NOT_CREATE.getCode(), CommonStatusEnum.ORDER_NOT_CREATE.getValue());
        }
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);


        orderInfoMapper.insert(orderInfo);
        // 派单 dispatchRealTimeOrder
        dispatchRealTiemOrder(orderInfo);
        return ResponseResult.success("");
    }

    /**
     * 实时订单派单逻辑
     * @param orderInfo
     */
    public void dispatchRealTiemOrder(OrderInfo orderInfo){
        //2km
        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        String center = depLatitude + "," + depLongitude;
        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);
        ResponseResult<List<TerminalResponse>> aroundsearch;
        redus:
        for(int i = 0; i <radiusList.size(); i++){
            Integer radius = radiusList.get(i);
            aroundsearch = serviceMapClient.aroundsearch(center, radius);
            log.info("在半径为：" + radius + "的范围内，寻找车辆，结果：" + JSONObject.fromObject(aroundsearch.getData().get(0)).toString());

            // 获取终端
            List<TerminalResponse> data = aroundsearch.getData();
            JSONArray result = JSONArray.fromObject(aroundsearch.getData());
            for (int j = 0; j < data.size(); j++){
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getCarId();
                String longitude = terminalResponse.getLongitude();
                String latitude = terminalResponse.getLatitude();

                // 根据车辆的id，查询是否有可派单的司机
                ResponseResult<OrderDriverResponse> availableDriver = servicDriverUserClient.getAvailableDriver(carId);
                // 如果司机为空
                if(availableDriver.getCode() == CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()){
                    log.info("没有处理车辆ID" + carId + "，对应的司机");
                    continue ;
                }else {
                    log.info("车辆ID：" + carId + "找到了正常出车的司机");
                    OrderDriverResponse orderDriverResponse = availableDriver.getData();
                    String driverPhone = orderDriverResponse.getDriverPhone();
                    Long driverId = orderDriverResponse.getDriverId();
                    String licenseId = orderDriverResponse.getLicenseId();
                    String vehicleNo = orderDriverResponse.getVehicleNo();
                    // 锁司机ID小技巧，使用intern是我们从常量池中获取。
                    synchronized ((driverId+"").intern()){
                        //判断乘客有正在进行的订单不允许下单
                        if(isDriverOrderGoingon(orderDriverResponse.getDriverId()) > 0){
                            continue;
                        }
                        // 订单直接匹配司机
                        //查询当前车辆信息
                        //查询当前司机信息
                        orderInfo.setDriverId(driverId);
                        orderInfo.setDriverPhone(driverPhone);
                        orderInfo.setCarId(carId);

                        //从地图中来
                        orderInfo.setReceiveOrderCarLongitude(longitude);
                        orderInfo.setReceiveOrderCarLatitude(latitude);

                        orderInfo.setReceiveOrderTime( LocalDateTime.now());
                        orderInfo.setLicenseId(licenseId);
                        orderInfo.setVehicleNo(vehicleNo);
                        orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);
                        orderInfoMapper.updateById(orderInfo);
                        //退出不在进行司机的查找
                        break redus;
                    }
                }

            }
            //解析终端

            // 根据解析出来的终端，查询车辆信息

            // 找到符合的车辆，进行派单

            // 如果派单成功，则退出循环

        }

    }

    private boolean isBlackDevice(String deviceCodeKey) {
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if(aBoolean){
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if(i >= 2){
                //当前设备超过下单次数
                return true;
            }else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        }else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey,"1",1L, TimeUnit.HOURS);
        }
        return false;
    }

    /**
     * 判断乘客有正在进行的订单不允许下单
     * @param passengerId
     * @return
     */
    private int isPassengerOrderGoingon(Long passengerId){
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);
        queryWrapper.and(wrapper ->wrapper.eq("order_status",OrderConstants.ORDER_START).
                or().eq("order_status",OrderConstants.DRIVER_RECEIVE_ORDER).
                or().eq("order_status",OrderConstants.DRIVER_TO_PICK_UP_PASSENGER).
                or().eq("order_status",OrderConstants.DRIVER_ARRIVED_DEPARTURE).
                or().eq("order_status",OrderConstants.PICK_UP_PASSENGER).
                or().eq("order_status",OrderConstants.PASSENGER_GETOFF).
                or().eq("order_status",OrderConstants.TO_START_PAY).
                or().eq("order_status",OrderConstants.SUCCESS_PAY)
        );
        Integer validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        return validOrderNumber;
    }

    /**
     * 判断司机是否有正在进行的订单
     * @param driverId
     * @return
     */
    private int isDriverOrderGoingon(Long driverId){
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverId);
        queryWrapper.and(wrapper ->wrapper.eq("order_status",OrderConstants.DRIVER_RECEIVE_ORDER).
                or().eq("order_status",OrderConstants.DRIVER_TO_PICK_UP_PASSENGER).
                or().eq("order_status",OrderConstants.DRIVER_ARRIVED_DEPARTURE).
                or().eq("order_status",OrderConstants.PICK_UP_PASSENGER)
        );
        Integer validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        log.info("司机ID：" + driverId + ",正在进行的订单的数量：" + validOrderNumber);
        return validOrderNumber;
    }

}
