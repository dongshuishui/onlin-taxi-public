package com.dongshuishui.servicemap.remote;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.response.ServiceResponse;
import com.dongshuishui.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 东水水
 * @Date: 2023/2/20  17:14
 * @Description: com.dongshuishui.servicemap.remote
 * @Version: 1.0
 */
@Service
@Slf4j
public class TerminalClient {
    @Value("${amap.key}")
    private String amapkey;
    /**
     * 服务id
     */
    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;


    public ResponseResult add(String name, String desc){
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.TERMINAL_ADD_RUL);
        urlBuilder.append("?");
        urlBuilder.append("key=" + amapkey);
        urlBuilder.append("&");
        urlBuilder.append("sid=" + amapSid);
        urlBuilder.append("&");
        urlBuilder.append("name=" + name);
        urlBuilder.append("&");
        urlBuilder.append("desc=" + desc);

        log.info("创建终端请求" + urlBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(urlBuilder.toString(),null, String.class);
        log.info("创建终端响应" + forEntity.getBody());

        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);

        JSONObject data = result.getJSONObject("data");

        String sid = data.getString("sid");
        String tid = data.getString("tid");
        String terminalName = data.getString("name");
        TerminalResponse terminalResponse = new TerminalResponse();

        terminalResponse.setSid(sid);
        terminalResponse.setTid(tid);
        terminalResponse.setName(terminalName);
        return ResponseResult.success(terminalResponse);
    }
}
