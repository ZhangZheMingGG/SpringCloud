package com.kuang.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  // 相当于spring里面的applicationContext.xml
public class ConfigBean {

    // 配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced // 配置负载均衡实现RestTemplate
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        //使用我们重新选择的随机算法，替代默认的轮询！
        return new RandomRule();
    }
}
