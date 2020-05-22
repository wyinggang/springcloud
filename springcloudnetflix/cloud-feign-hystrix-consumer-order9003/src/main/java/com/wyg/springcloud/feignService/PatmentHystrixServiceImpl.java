package com.wyg.springcloud.feignService;

import org.springframework.stereotype.Component;

@Component
public class PatmentHystrixServiceImpl implements FeignPaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PatmentHystrixServiceImpl---------paymentInfo_OK---------fall back";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PatmentHystrixServiceImpl---------paymentInfo_TimeOut---------fall back";
    }
}
