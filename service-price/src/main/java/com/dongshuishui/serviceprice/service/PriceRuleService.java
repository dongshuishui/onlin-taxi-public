package com.dongshuishui.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.dto.PriceRule;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.serviceprice.mapper.PriceRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  计价规制服务类
 * </p>
 *
 * @author 东水水
 * @since 2023-02-22
 */
@Service
public class PriceRuleService {

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    /**
     * 添加计价规制
     * @param priceRule
     * @return
     */
    public ResponseResult add(PriceRule priceRule) {
        //获取城市代码
        String cityCode = priceRule.getCityCode();
        //获取
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" +vehicleType;
        priceRule.setFareType(fareType);

        //添加版本
        //问题1：添加版本号，前面的两个字段无法唯一缺点一条记录
        //问题2：找最大的版本号，需要排序
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if(priceRules.size() > 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(),CommonStatusEnum.PRICE_RULE_EXISTS.getValue());
        }

        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success("");
    }


    public ResponseResult edit(PriceRule priceRule){
        //获取城市代码
        String cityCode = priceRule.getCityCode();
        //获取
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" +vehicleType;
        priceRule.setFareType(fareType);

        //添加版本
        //问题1：添加版本号，前面的两个字段无法唯一缺点一条记录
        //问题2：找最大的版本号，需要排序
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if(priceRules.size() > 0){

            PriceRule lasterPriceRule = priceRules.get(0);
            Double unitPricePerMile = lasterPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = lasterPriceRule.getUnitPricePerMinute();
            Double startFare = lasterPriceRule.getStartFare();
            Integer startMile = lasterPriceRule.getStartMile();

            if(unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile().doubleValue()
                    && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute().doubleValue()
                    && startFare.doubleValue() == priceRule.getStartFare().doubleValue()
                    && startMile.intValue() == priceRule.getStartMile().intValue()){
                return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_EDIT.getCode(),CommonStatusEnum.PRICE_RULE_NOT_EDIT.getValue());
            }
            fareVersion = priceRules.get(0).getFareVersion();
        }
        priceRule.setFareVersion(++fareVersion);

        priceRuleMapper.insert(priceRule);
        return ResponseResult.success("");
    }
}
