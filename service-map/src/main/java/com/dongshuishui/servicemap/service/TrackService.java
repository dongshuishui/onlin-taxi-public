package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/21  8:42
 * @Description: com.dongshuishui.servicemap.service
 * @Version: 1.0
 */
@Service
public class TrackService {
    @Autowired
    private TrackClient trackClient;

    /**
     * 新增终端轨迹
     * @param tid
     * @return
     */
    public ResponseResult add(String tid){
        return trackClient.add(tid);
    }
}
