package com.wyg.springcloud.controller;

import com.wyg.springcloud.feignService.FeignPaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
//全局降级方法
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private FeignPaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }
//
//    //失败,抛出异常或者，超时都不调用paymentInfo_TimeOutHandler方法，（这种方法每个方法都会写降级方法，代码膨胀，并且与业务代码耦合）
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
//            //调用当前方法超过3s会报错
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
   // @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("*******result:"+result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "9003调用8004接口超时或异常,请稍后再试";
    }

    //下面是全局fallback方法,当具体某个方法上指定了降级方法时会调用指定方法,
    // 但是降级方法任然与业务代码耦合--->在FeignPaymentHystrixService注解上配置统一异常处理类
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }
}
