package com.wyg.springcloud.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为path_routr_wyg的路由规则
     * 当访问http://localhost:9527/guonei时会自动转发到http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customerLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        RouteLocator build = routes.route("path_routr_wyg", e -> e.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return build;
    }

}
