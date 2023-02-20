package com.dongshuishui.servicemap.controller;

import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @Author: 东水水
 * @Date: 2023/2/20  17:12
 * @Description: com.dongshuishui.servicemap.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;

    @PostMapping("/add")
    public ResponseResult add(String name){
        return terminalService.add(name);
    }
}
