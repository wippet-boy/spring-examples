package com.jrg.helloworld;

import com.jrg.helloworld.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    @Qualifier("helloGateway")
    private HelloService helloGateway;

    @GetMapping("/name")
    public String index(@RequestParam String name) {

       return  helloGateway.sayHello(name);
    }


}