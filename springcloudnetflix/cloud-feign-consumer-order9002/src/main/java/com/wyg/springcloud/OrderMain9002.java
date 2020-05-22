package com.wyg.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain9002.class,args);
    }
}
