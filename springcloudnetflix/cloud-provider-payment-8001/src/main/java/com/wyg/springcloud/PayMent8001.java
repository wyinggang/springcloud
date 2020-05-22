package com.wyg.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication()
public class PayMent8001 {

    public static void main(String[] args) {
        SpringApplication.run(PayMent8001.class,args);
    }

}
