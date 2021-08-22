package com.jrg.helloworld.service;

public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
