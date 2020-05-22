package com.wyg.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PaymentService {

    //成功
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    //失败,抛出异常或者，超时都不调用paymentInfo_TimeOutHandler方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //调用当前方法超过3s会报错
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 3;
        //int i = 10 / 0;
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id){

         return "8004调用接口超时或异常,请稍后再试";
    }


    //==================================>服务熔断

    /**
     * 在时间范围内，（阈值）请求次数（10）的 60%（6） 失败，断路器将会从close ---> open ，
     * 在这个时间范围类，如果有新的请求进入 half open 状态，尝试进行请求如果成功就恢复close状态，失败继续保持 open状态
     *
     * 默认参数： 阈值：默认10s 内请求超过 20
     *           失败率：默认 50 %
     *          ---达到以上两个条件断路器进入open状态---
     *          open时使用请求都不会转发,会直接调用降级方法
     *          一段时间后（默认5s）进入half-open，让其中一个请求进行转发（调用主逻辑），成功进入close，失败进入open
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }

    //熔断时的降级方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }


}


