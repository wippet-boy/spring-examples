package com.jrg.helloworld.service;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name="helloGateway", defaultRequestChannel = "channel")
public interface HelloService {
    String sayHello (String name);
}
