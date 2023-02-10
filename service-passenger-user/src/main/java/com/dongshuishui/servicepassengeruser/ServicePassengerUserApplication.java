package com.dongshuishui.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  20:19
 * @Description: com.dongshuishui
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.dongshuishui.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}
