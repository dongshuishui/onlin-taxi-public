package com.dongshuishui.apiboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  19:41
 * @Description: com.dongshuishui.apiboss
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiBossApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBossApplication.class);
    }
}
