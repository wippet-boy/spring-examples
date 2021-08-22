package com.jrg.helloworld;

import com.jrg.helloworld.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jrg.helloworld")
public class Application implements CommandLineRunner{

    @Autowired
    @Qualifier("helloGateway")
    private HelloService helloGateway;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloGateway.sayHello("Soumitra"));
    }

}