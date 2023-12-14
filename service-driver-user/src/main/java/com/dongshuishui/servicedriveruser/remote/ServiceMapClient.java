package com.dongshuishui.servicedriveruser.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.TerminalResponse;
import com.dongshuishui.internalcommon.response.TrackTesponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 东水水
 * @Date: 2023/2/20  17:51
 * @Description: com.dongshuishui.servicedriveruser.remote
 * @Version: 1.0
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST, value = "/terminal/add")
    public ResponseResult<TerminalResponse> addTerminal(@RequestParam String name, @RequestParam String desc);

    @RequestMapping(method = RequestMethod.POST, value = "/track/add")
    public ResponseResult<TrackTesponse> addTrack(@RequestParam String tid);
}
