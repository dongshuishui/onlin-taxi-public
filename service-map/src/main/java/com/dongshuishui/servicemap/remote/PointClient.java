package com.dongshuishui.servicemap.remote;

import com.dongshuishui.internalcommon.constant.AmapConfigConstants;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.request.PointDTO;
import com.dongshuishui.request.PointRequest;
import com.dongshuishui.response.TrackTesponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.net.URI;

/**
 * 轨迹点上传（单点、批量）调用高德-猎鹰接口
 *
 * 接口说明
 * 1、可以将终端的轨迹点通过经纬度上传接口进行上传，支持批量上传以及单点上传。
 *
 * 2、在上传经纬度之前需要先通过创建轨迹接口创建一条轨迹，拿到trid，根据trid上传经纬度点；
 *  如果用户指定了trid（轨迹id），但是trid不存在，该点按照trid字段为空存储，并返回对应的错误信息：
 *  trid不存在，点已存储，此时点信息会绑定在tid上，不会生成轨迹。
 *
 * 3、若一次上传多个点，其中有一个/多个出错时，服务会进行报错，但是正确的点会上传到服务器之中可以正常使用，
 *  并且在结果之中会显示出错点的序号。例如：用户上传了A、B、C、D、E 这五个点，其中C点的数据是错误的，
 *  服务会返回报错结果，及序号：3；但是A、B、D、E这4个点已经成功上传
 *
 * @Author: 东水水
 * @Date: 2023/2/21  11:03
 * @Description: com.dongshuishui.servicemap.remote
 * @Version: 1.0
 */
@Service
public class PointClient {

    @Value("${amap.key}")
    private String amapkey;
    /**
     * 服务id
     */
    @Value("${amap.sid}")
    private String amapSid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult upload(PointRequest pointRequest){

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.POINT_UPLOAD);
        urlBuilder.append("?");
        urlBuilder.append("key=" + amapkey);
        urlBuilder.append("&");
        urlBuilder.append("sid=" + amapSid);
        urlBuilder.append("&");
        urlBuilder.append("tid=" + pointRequest.getTid());
        urlBuilder.append("&");
        urlBuilder.append("trid=" + pointRequest.getTrid());
        urlBuilder.append("&");
        urlBuilder.append("points=");
        PointDTO[] pointDTOs = pointRequest.getPoints();
        urlBuilder.append("%5B");
        for (PointDTO pointDTO : pointDTOs) {
            urlBuilder.append("%7B");
            String location = pointDTO.getLocation();
            String locatetime = pointDTO.getLocatetime();
            urlBuilder.append("%22location%22" );
            urlBuilder.append("%3A");
            urlBuilder.append("%22" +location+ "%22");
            urlBuilder.append("%2C");
            urlBuilder.append("%22locatetime%22" );
            urlBuilder.append("%3A");
            urlBuilder.append(locatetime);
            urlBuilder.append("%7D");
        }
        urlBuilder.append("%5D");

        System.out.println("上传位置请求" + urlBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(URI.create(urlBuilder.toString()),null, String.class);
        System.out.println("上传位置响应" + forEntity.getBody());

        return ResponseResult.success("");
    }

}
