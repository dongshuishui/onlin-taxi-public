package com.dongshuishui.apidriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  22:36
 * @Description: com.dongshuishui.apidriver
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiDriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDriverApplication.class);
    }
}
