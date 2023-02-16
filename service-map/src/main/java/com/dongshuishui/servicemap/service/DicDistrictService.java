package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import com.dongshuishui.internalcommon.dto.DicDistrict;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.mapper.DicDistrictMapper;
import com.dongshuishui.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords){
        //请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrictResult);
        //解析结果
        JSONObject  dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if(status != 1){
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(),CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        //国家
        JSONArray countyJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int i = 0; i < countyJsonArray.size(); i++){
            JSONObject countyJsonObject = countyJsonArray.getJSONObject(i);
            String countyAddrssCode = countyJsonObject.getString(AmapConfigConstants.ADCODE);
            String countyAddressName = countyJsonObject.getString(AmapConfigConstants.NAME);
            String countyParentAddressCode = "0";
            String countyLevel = countyJsonObject.getString(AmapConfigConstants.LEVEL);

            insertDicDistrict(countyAddrssCode, countyAddressName, countyLevel, countyParentAddressCode);

            //省份
            JSONArray proviceJsonArray = countyJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int p = 0; p < proviceJsonArray.size() ;p++){
                JSONObject proviceJsonObject = proviceJsonArray.getJSONObject(p);
                String proviceAddrssCode = proviceJsonObject.getString(AmapConfigConstants.ADCODE);
                String proviceAddressName = proviceJsonObject.getString(AmapConfigConstants.NAME);
                String proviceParentAddressCode = countyAddrssCode;
                String proviceLevel = proviceJsonObject.getString(AmapConfigConstants.LEVEL);

                insertDicDistrict(proviceAddrssCode, proviceAddressName, proviceLevel, proviceParentAddressCode);

                //城市
                JSONArray cityJsonArray = proviceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int c = 0; c < cityJsonArray.size() ; c++){
                    JSONObject cityJsonObject = cityJsonArray.getJSONObject(c);
                    String cityAddrssCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = proviceAddrssCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);

                    insertDicDistrict(cityAddrssCode, cityAddressName, cityLevel, cityParentAddressCode);

                    JSONArray districtJsonArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0; d < districtJsonArray.size() ; d++){
                        JSONObject districtJsonObject = districtJsonArray.getJSONObject(d);
                        String districtAddrssCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddrssCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);
                        if(AmapConfigConstants.STREET.equals(districtLevel)){
                            continue;
                        }
                        insertDicDistrict(districtAddrssCode, districtAddressName, districtLevel, districtParentAddressCode);

                    }
                }
            }
        }

        return ResponseResult.success("");
    }

    public void insertDicDistrict(String addrssCode, String addressName, String level, String parentAddressCode){
        //数据库对象
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addrssCode);
        dicDistrict.setAddressName(addressName);
        int levelInt = generateLevel(level);
        dicDistrict.setLevel(levelInt);
        dicDistrict.setParentAddressCode(parentAddressCode);

        //插入数据库
        dicDistrictMapper.insert(dicDistrict);
    }

    public int generateLevel(String level){
        int levelInt = 0;
        String levelTrim = level.trim();
        if("country".equals(levelTrim)){
            levelInt = 0;
        }else if("province".equals(levelTrim)){
            levelInt = 1;
        }else if("city".equals(levelTrim)){
            levelInt = 2;
        }else if("district".equals(levelTrim)){
            levelInt = 3;
        }
        return levelInt;
    }
}
