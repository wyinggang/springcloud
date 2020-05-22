package com.wyg.springcloud.controller;

import com.wyg.springcloud.entities.CommonResult;
import com.wyg.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.wyg.springcloud.service.PaymentService;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if (i>0){
            return new CommonResult(200,"创建成功"+serverPort+"", i);
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
