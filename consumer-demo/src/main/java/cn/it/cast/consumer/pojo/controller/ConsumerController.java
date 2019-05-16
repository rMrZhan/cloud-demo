package cn.it.cast.consumer.pojo.controller;

import cn.it.cast.consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consume")
@Slf4j
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id){
        log.info("hello world");
        String url = "http://localhost:8081/user/" + id;
        return restTemplate.getForObject(url, User.class);
    }
}
