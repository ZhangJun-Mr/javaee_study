package com.webservice.service;

import com.webservice.pojo.Greeting;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author someone
 */
@WebService
public interface GreetingService {
    @WebMethod
    @WebResult(name = "Greeting")
    Greeting sayHello(@WebParam(name = "GreetingsRequest") String name);

    @WebMethod
    @WebResult(name = "Greeting")
    Greeting sayBye(@WebParam(name = "GreetingsRequest") String name);
}
