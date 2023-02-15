package com.dongshuishui.servicemap.remote;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 东水水
 * @Date: 2023/2/15  19:57
 * @Description: com.dongshuishui.servicemap.remote
 * @Version: 1.0
 */
@Service
public class MapDicDistrictClient {


    @Value("${amap.key}")
    private String amapkey;

    @Autowired
    private RestTemplate restTemplate;

    public String dicDistrict(String keywords){
        //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        //拼装请求url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DISTRICT_RUL);
        urlBuilder.append("?");
        urlBuilder.append("keywords=" + keywords);
        urlBuilder.append("&");
        urlBuilder.append("subdistrict=3");
        urlBuilder.append("&");
        urlBuilder.append("key=" + amapkey);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);


        return forEntity.getBody();
    }
}
