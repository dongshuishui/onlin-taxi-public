package com.dongshuishui.serviceprice.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 * @Author: 东水水
 * @Date: 2023/2/17  14:02
 * @Description: com.dongshuishui.servicedriveruser.genertor
 * @Version: 1.0
 */
public class MysqlGenertor {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://192.168.182.11:3306/service-price?characterEncoding=utf-8&serverTimezne=GMT%2B8",
                "root","123456").globalConfig(builder -> {
                    builder.author("东水水").fileOverride().outputDir("E:\\idea-work\\idea-work2\\onlin-taxi-public\\service-price\\src\\main\\java");
        }).packageConfig(builder -> {
            builder.parent("com.dongshuishui.serviceprice").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                    "E:\\idea-work\\idea-work2\\onlin-taxi-public\\service-price\\src\\main\\java\\com\\dongshuishui\\serviceprice\\mapper"));
        }).strategyConfig(builder -> {
            builder.addInclude("price_rule");
        }).templateEngine(new FreemarkerTemplateEngine()).execute();

    }
}
