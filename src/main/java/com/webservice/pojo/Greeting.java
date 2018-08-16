package com.webservice.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Greeting")
public class Greeting {
    private String message;
    private Date date;
}
