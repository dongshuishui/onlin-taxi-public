package com.dongshuishui.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongshuishui.internalcommon.dto.DriverUser;
import org.springframework.stereotype.Repository;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  16:15
 * @Description: com.dongshuishui.servicedriveruser.mapper
 * @Version: 1.0
 */

@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {
    /**
     * 根据cityCode查询当前城市是否有可用司机
     * @param arg
     * @return
     */
    int selectDriverUserCountByCityCode(String arg);
}
