package com.wyg.springcloud.controller;

import com.wyg.springcloud.entities.CommonResult;
import com.wyg.springcloud.entities.Payment;
import com.wyg.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/create")
    public CommonResult create(Payment payment){
        int i = paymentService.create(payment);
        if (i>0){
            return new CommonResult(200,"cg", i);
        }
        return new CommonResult(500,"sb",i);
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id")long id){
        Payment payment = paymentService.getPayment(id);
        if (!ObjectUtils.isEmpty(payment)){
            return new CommonResult(200,"成功",payment);
        }
       return new CommonResult(500,"失败");
    }

}
