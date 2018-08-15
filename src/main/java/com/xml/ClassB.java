package com.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.NONE)
public class ClassB {
    @XmlAttribute
    private String attrUserName;
    @XmlAttribute
    private String attrPassword;
    @XmlElement
    private String eleCode;

    public String getAttrUserName() {
        return attrUserName;
    }

    public void setAttrUserName(String attrUserName) {
        this.attrUserName = attrUserName;
    }

    public String getAttrPassword() {
        return attrPassword;
    }

    public void setAttrPassword(String attrPassword) {
        this.attrPassword = attrPassword;
    }

    public String getEleCode() {
        return eleCode;
    }

    public void setEleCode(String eleCode) {
        this.eleCode = eleCode;
    }
}
