package com.wyg.springcloud.controller;

import com.wyg.springcloud.entities.CommonResult;
import com.wyg.springcloud.entities.Payment;
import com.wyg.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if (i>0){
            return new CommonResult(200,"成功"+serverPort, i);
        }
        return new CommonResult(500,"sb",i);
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id")long id){
        Payment payment = paymentService.getPayment(id);
        if (!ObjectUtils.isEmpty(payment)){
            System.out.println("111111111133333333333");
            return new CommonResult(200,"成功"+serverPort,payment);
        }
       return new CommonResult(500,"失败");
    }


    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("--------"+service+"---------");
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB(){
        return serverPort;
    }



    @GetMapping("/timeout")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
