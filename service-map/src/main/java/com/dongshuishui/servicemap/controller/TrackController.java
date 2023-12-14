package com.dongshuishui.servicemap.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 轨迹
 * @Author: 东水水
 * @Date: 2023/2/21  8:40
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private TrackService trackService;

    /**
     * 轨迹添加
     * @param tid
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String tid){
        return trackService.add(tid);
    }



    /**
     * 轨迹删除
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult delete(String tid,String trid){
        return trackService.delete(tid, trid);
    }
}
