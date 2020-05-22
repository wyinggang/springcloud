package com.wyg.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ErekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(ErekaMain7001.class);
    }
}
