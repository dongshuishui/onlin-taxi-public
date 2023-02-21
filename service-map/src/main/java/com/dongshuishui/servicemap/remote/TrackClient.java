package com.dongshuishui.servicemap.remote;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.TerminalResponse;
import com.dongshuishui.response.TrackTesponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  8:43
 * @Description: com.dongshuishui.servicemap.remote
 * @Version: 1.0
 */
@Service
public class TrackClient {
    @Value("${amap.key}")
    private String amapkey;
    /**
     * 服务id
     */
    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;


    public ResponseResult add(String tid){
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.TRACK_ADD_RUL);
        urlBuilder.append("?");
        urlBuilder.append("key=" + amapkey);
        urlBuilder.append("&");
        urlBuilder.append("sid=" + amapSid);
        urlBuilder.append("&");
        urlBuilder.append("tid=" + tid);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(urlBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        //轨迹id
        String trid = data.getString("trid");
        //轨迹名称
        String trname = "";
        if (data.has("trname")){
            trname = data.getString("trname");
        }
        TrackTesponse trackTesponse = new TrackTesponse();
        trackTesponse.setTrid(trid);
        trackTesponse.setTrname(trname);

        return ResponseResult.success(trackTesponse);
    }
}
