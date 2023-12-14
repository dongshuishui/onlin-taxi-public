package com.dongshuishui.internalcommon.request;

import lombok.Data;

/**
 * @author：东水水
 * @createTime：2023/12/14 -1:40
 * @describe: com.dongshuishui.internalcommon.request
 */
@Data
public class PriceRuleIsNewRequest {

    private String fareType;

    private Integer fareVersion;
}
