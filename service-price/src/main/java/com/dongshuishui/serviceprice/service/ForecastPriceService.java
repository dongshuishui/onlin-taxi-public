package com.dongshuishui.serviceprice.service;

import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.dto.PriceRule;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.ForecastPriceDTO;
import com.dongshuishui.response.DirectionResponse;
import com.dongshuishui.response.ForecastPriceResponse;
import com.dongshuishui.serviceprice.mapper.PriceRuleMapper;
import com.dongshuishui.serviceprice.remote.ServiceMapClient;
import com.dongshuishui.util.BigDecimalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  15:55
 * @Description: com.dongshuishui.serviceprice.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        log.info("出发地经度：" + depLongitude +",出发地纬度：" + depLatitude );
        log.info("目的地经度：" + destLongitude + ",目的地纬度：" + destLatitude);

        log.info("调用地图服务，查询地图和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离：" + distance + ",时长：" + duration);

        log.info("读取计价规则");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", "110000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if(priceRules.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离、时长和计价规则，计算价格");
        double price = getPrice(distance, duration, priceRule);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离和时长计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule){

        double price = 0.0;
        //起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price, startFare);
        //里程费
        //总里程 km
        double distanceMile = BigDecimalUtils.divide(distance,1000);
        //起步里程
        double startMile = (double) priceRule.getStartMile();
        //最终收费的里程数 km
        double distanceSubtract = BigDecimalUtils.subtract(distanceMile, startMile);
        double mile = distanceSubtract < 0 ? 0 : distanceSubtract;

        //计程单价 元/Km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        //里程价格
        double milePrice = BigDecimalUtils.multiply(mile, unitPricePerMile);
        price = BigDecimalUtils.add(price, milePrice);

        //时长费
        //时长分钟数
        double timeMinute =BigDecimalUtils.divide(duration, 60);

        //计时单价
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        //时长费用
        double timeFare = BigDecimalUtils.multiply(timeMinute, unitPricePerMinute);
        //总费用
        price = BigDecimalUtils.add(price, timeFare);

        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);
        priceBigDecimal = priceBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return priceBigDecimal.doubleValue();
    }

/*    public static void main(String[] args) {
        PriceRule priceRule = new PriceRule();
        priceRule.setUnitPricePerMile(1.8);
        priceRule.setUnitPricePerMinute(0.5);
        priceRule.setStartFare(10.0);
        priceRule.setStartMile(3);
        System.out.println(getPrice(6500, 1800, priceRule));
    }*/

}
