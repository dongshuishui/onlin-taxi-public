package com.dongshuishui.serviceorder.remote;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.internalcommon.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author：东水水
 * @createTime：2023/12/13 -15:19
 * @describe: com.dongshuishui.serviceorder.remote
 */
@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.POST,value = "/terminal/aroundsearch")
    ResponseResult<List<TerminalResponse>> aroundsearch(@RequestParam String center, @RequestParam Integer radius);
}
