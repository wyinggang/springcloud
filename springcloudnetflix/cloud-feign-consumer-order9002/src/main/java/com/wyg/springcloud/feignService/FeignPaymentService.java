package com.wyg.springcloud.feignService;

import com.wyg.springcloud.entities.CommonResult;
import com.wyg.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//feign默认整合ribbon实现负载均衡
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface FeignPaymentService {

    @GetMapping("/payment/getPaymentById/{id}")
    CommonResult<Payment> getPayment(@PathVariable("id") long id);

    @GetMapping("/payment/timeout")
    String paymentFeignTimeOut();

}
