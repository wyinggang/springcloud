package com.wyg.springcloud.controller;

import com.wyg.springcloud.entities.CommonResult;
import com.wyg.springcloud.entities.Payment;
import com.wyg.springcloud.feignService.FeignPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderFeignController {

    @Autowired
    private FeignPaymentService feignPaymentService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return feignPaymentService.getPayment(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String paymentFeignTimeOut(){
        //openFeign客户端默认等待1s，服务方进行3s延时，会超时报超时错误---> 可以在yml文件设置feign超时时间
        return feignPaymentService.paymentFeignTimeOut();
    }
}
