package com.dongshuishui.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: 东水水
 * @Date: 2023/2/8  17:11
 * @Description: com.dongshuishui.apipassenger
 * @Version: 1.0
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ApiPassengerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class);
    }
}

