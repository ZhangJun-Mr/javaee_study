package com.webservice.service;

import com.webservice.pojo.Greeting;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface InforService {
    @WebMethod
    @WebResult(name = "Greeting")
    Greeting sayHowAreYou(@WebParam(name = "GreetingsRequest")String name);
}
