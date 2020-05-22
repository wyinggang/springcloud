package com.wyg.springcloud.controller;


import com.wyg.springcloud.entities.CommonResult;
import com.wyg.springcloud.entities.Payment;
import com.wyg.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.swing.border.EtchedBorder;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
      public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("----------------create----------------");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getpayMent(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
