package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    /**
     * 思考： 消费者里面是不应该有service 层，那么我们如何调到提供者的接口呢？
     * 可以使用RestTemplate,它提供了我们可以直接调用。但是看源码并没有将RestTemplate 交给Spring管理
     * 那我们怎么使用呢？ 自定义配置 注入进来即可。ConfigBean。
     */
    @Autowired
    private RestTemplate restTemplate; //提供多种便捷访问http服务的方法，简单的restful服务模板

    //通过Ribbon，这里应该是一个变量，通过服务名来访问
//    private static final String REST_URL_PREFIX = "http://localhost:8082";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id,Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list",List.class);
    }


}
