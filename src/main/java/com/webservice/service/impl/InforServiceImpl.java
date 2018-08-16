package com.webservice.service.impl;

import com.webservice.pojo.Greeting;
import com.webservice.service.InforService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author someone
 */
@Service
public class InforServiceImpl implements InforService {
    @Override
    public Greeting sayHowAreYou(String name) {
        Greeting greeting = new Greeting();
        greeting.setMessage("How are you " + name + "!!!");
        greeting.setDate(new Date());
        return greeting;
    }
}
