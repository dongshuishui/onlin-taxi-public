package com.dongshuishui.servicemap.remote;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.reponse.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  17:54
 * @Description: com.dongshuishui.servicemap.remote
 * @Version: 1.0
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapkey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装参数请求调用url
        //https://restapi.amap.com/v3/direction/driving?
        // origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=xml&key=fac7e9d39088e3e11b36a56c3671ad69
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?");
        urlBuild.append("origin=" + depLongitude + "," + depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination=" + destLongitude + "," + destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=base");
        urlBuild.append("&");
        urlBuild.append("output=json");
        urlBuild.append("&");
        urlBuild.append("key=" + amapkey);
        log.info(urlBuild.toString());

        //调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String directionString = directionEntity.getBody();

        log.info("高德地图：路径规划，返回信息：" + directionEntity.getBody());
        //解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionString);

        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse = null;
        try {
            //最外层
            JSONObject result = JSONObject.fromObject(directionString);
            if(result.has(AmapConfigConstants.STATUS)){
                int status = result.getInt(AmapConfigConstants.STATUS);
                if(status == 1){
                    if(result.has(AmapConfigConstants.ROUTE)){
                        JSONObject jsonRouteObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray jsonPathsArray = jsonRouteObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathsJSONObject = jsonPathsArray.getJSONObject(0);
                        directionResponse = new DirectionResponse();
                        if(pathsJSONObject.has(AmapConfigConstants.DISTANCE)){
                            int distance = pathsJSONObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if(pathsJSONObject.has(AmapConfigConstants.DURATION)){
                            int duration = pathsJSONObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }

        }catch (Exception e){
            System.out.println("异常！");
        }
        return directionResponse;
    }
}
