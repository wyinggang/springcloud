package com.wyg.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置ribbon的负载均衡规则，注意包路径不能在主启动类ComponentScan扫描路径下
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
