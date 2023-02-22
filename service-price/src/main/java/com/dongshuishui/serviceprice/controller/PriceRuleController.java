package com.dongshuishui.serviceprice.controller;


import com.dongshuishui.internalcommon.dto.PriceRule;
import com.dongshuishui.internalcommon.dto.ResponseResult;
import com.dongshuishui.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 东水水
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {
    @Autowired
    private PriceRuleService priceRuleService;

    /**
     * 添加计价规制
     * @param priceRule
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){

        return priceRuleService.add(priceRule);
    }
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){
        return priceRuleService.edit(priceRule);
    }

}
