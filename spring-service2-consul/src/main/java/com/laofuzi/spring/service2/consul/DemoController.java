package com.laofuzi.spring.service2.consul;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
@RequestMapping("/")
public class DemoController {
	
    @GetMapping("test")
    public String hello() {  
        return "hello, this is service1 instance2";
    } 
    
    @PostMapping("test-post")
    public String helloPost() {  
        return "hello, this is service1 instance2";
    } 
    
    @PostMapping("/v1/service/service1")
    public String service1() {  
        return "hello, this is service1 instance2";
    }
    
}
