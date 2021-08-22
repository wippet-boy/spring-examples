package com.jrg.helloworld;

import com.jrg.helloworld.service.HelloService;
import com.jrg.helloworld.service.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class SpringConfig {

    @Bean("channel")
    public MessageChannel channel() {
        return new DirectChannel();
    }

    @Bean
    HelloService helloService () {
        return new HelloServiceImpl();
    }

    @Bean
    @ServiceActivator (inputChannel = "channel")
    public MessageHandler servceActivator () {
        return new ServiceActivatingHandler(helloService(), "sayHello");
    }
}
