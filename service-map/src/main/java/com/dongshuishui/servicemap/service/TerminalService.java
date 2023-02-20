package com.dongshuishui.servicemap.service;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 东水水
 * @Date: 2023/2/20  17:13
 * @Description: com.dongshuishui.servicemap.service
 * @Version: 1.0
 */
@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    /**
     * 添加终端
     * @param name
     * @return
     */
    public ResponseResult add(String name){

        return terminalClient.add(name);
    }
}
