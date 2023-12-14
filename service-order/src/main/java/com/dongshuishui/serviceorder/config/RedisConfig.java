package com.dongshuishui.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author：东水水
 * @createTime：2023/12/15 -0:40
 * @describe: com.dongshuishui.serviceorder.config
 */
@Component
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.31:6379").setDatabase(0);
        return Redisson.create(config);
    }
}
