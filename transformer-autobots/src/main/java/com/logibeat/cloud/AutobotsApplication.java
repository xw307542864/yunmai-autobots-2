package com.logibeat.cloud;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.logibeat.cloud.remote.service"})
@NacosPropertySource(dataId = "qtz", autoRefreshed = true)
public class AutobotsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutobotsApplication.class, args);
    }

}
