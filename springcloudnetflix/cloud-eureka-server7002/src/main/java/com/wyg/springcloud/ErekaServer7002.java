package com.wyg.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ErekaServer7002 {
    public static void main(String[] args) {
        SpringApplication.run(ErekaServer7002.class);
    }
}
