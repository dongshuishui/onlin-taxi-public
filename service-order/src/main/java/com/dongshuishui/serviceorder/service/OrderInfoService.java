package com.dongshuishui.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.request.OrderRequest;
import com.dongshuishui.internalcommon.constant.OrderConstants;
import com.dongshuishui.internalcommon.dto.OrderInfo;
import com.dongshuishui.internalcommon.util.RedisPrefixUtils;
import com.dongshuishui.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 新增订单
     * @param orderInfo
     * @return
     */
    public ResponseResult add(OrderInfo orderInfo) {

        //判断下单的设备是否位黑名单设备
        String deviceCode = orderInfo.getDeviceCode();
        //生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefx + deviceCode;
        //设置key，看看原来有没有
        if (isBlackDevice(deviceCodeKey)) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }
        //判断有正在进行的订单不允许下单
        if(isOrderGoingon(orderInfo.getPassengerId()) > 0){
            return ResponseResult.fail(CommonStatusEnum.ORDER_NOT_CREATE.getCode(), CommonStatusEnum.ORDER_NOT_CREATE.getValue());
        }
        /*orderInfo.setOrderStatus(OrderConstants.ORDER_START);

        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);


        orderInfoMapper.insert(orderInfo);*/
        return ResponseResult.success("");
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
     * 判断有正在进行的订单不允许下单
     * @param passengerId
     * @return
     */
    private int isOrderGoingon(Long passengerId){
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
}
