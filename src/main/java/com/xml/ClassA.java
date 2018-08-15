package com.xml;

import com.sun.tracing.dtrace.Attributes;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.NONE)
public class ClassA {
    @XmlElement
    private String eleA;
    @XmlElement
    private String eleB;
    @XmlAttribute
    private String attrC;

    public String getEleA() {
        return eleA;
    }

    public void setEleA(String eleA) {
        this.eleA = eleA;
    }

    public String getEleB() {
        return eleB;
    }

    public void setEleB(String eleB) {
        this.eleB = eleB;
    }

    public String getAttrC() {
        return attrC;
    }

    public void setAttrC(String attrC) {
        this.attrC = attrC;
    }
}
