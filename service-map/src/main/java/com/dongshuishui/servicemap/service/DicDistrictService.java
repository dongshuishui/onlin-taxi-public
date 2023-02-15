package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/15  19:33
 * @Description: com.dongshuishui.servicemap.service
 * @Version: 1.0
 */
@Service
public class DicDistrictService {
    @Value("${amap.key}")
    private String key;
    public ResponseResult initDicDistrict(String keywords){
        //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        //拼装请求url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DISTRICT_RUL);
        urlBuilder.append("?");
        urlBuilder.append("keywords=" + keywords);
        urlBuilder.append("&");
        urlBuilder.append("subdistrict=3");
        urlBuilder.append("&");
        urlBuilder.append("key=" + key);

        //解析结果

        //插入数据库

        return ResponseResult.success();
    }
}
