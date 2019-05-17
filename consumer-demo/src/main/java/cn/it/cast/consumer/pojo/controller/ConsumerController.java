package cn.it.cast.consumer.pojo.controller;

import cn.it.cast.consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
@Slf4j
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient  discoveryClient;
    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance instance = instances.get(0);
        log.info("hello world");
        String url = "http://"+ instance.getHost() + ":" + instance.getPort() +"/user/" + id;
        log.info("url ======"+url);
        return restTemplate.getForObject(url, User.class);
    }
}
