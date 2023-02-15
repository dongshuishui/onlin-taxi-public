package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.remote.MapDicDistrictClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;


    public ResponseResult initDicDistrict(String keywords){
        //请求地图
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrict);
        //解析结果


        //插入数据库

        return ResponseResult.success();
    }
}
