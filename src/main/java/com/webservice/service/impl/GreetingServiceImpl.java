package com.webservice.service.impl;

import com.webservice.pojo.Greeting;
import com.webservice.service.GreetingService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author someone
 */
@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public Greeting sayHello(String name) {
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello " + name + "!!!");
        greeting.setDate(new Date());
        return greeting;
    }

    @Override
    public Greeting sayBye(String name) {
        Greeting greeting = new Greeting();
        greeting.setMessage("Bye " + name + "!!!");
        greeting.setDate(new Date());
        return greeting;
    }
}
