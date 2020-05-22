package com.wyg.springcloud;

import com.wyg.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//自定义负载均衡规则
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
@EnableEurekaClient
@SpringBootApplication
public class Order9000 {
    public static void main(String[] args) {
        SpringApplication.run(Order9000.class);
    }
}
