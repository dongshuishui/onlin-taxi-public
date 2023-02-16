package com.dongshuishui.servicedriveruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 东水水
 * @Date: 2023/2/16  15:46
 * @Description: com.dongshuishui.servicedriveruser
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.dongshuishui.servicedriveruser.mapper")
public class ServiceDriverUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class);
    }
}
