package com.xml;

import javax.xml.bind.annotation.*;


@XmlRootElement
@XmlAccessorType(value = XmlAccessType.NONE)
public class XmlBody {
    @XmlElement
    private ClassA classA;
    @XmlElement
    private ClassB classB;
    @XmlElement
    private String rootA;
    @XmlElement
    private String rootB;

    public ClassA getClassA() {
        return classA;
    }

    public void setClassA(ClassA classA) {
        this.classA = classA;
    }

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public String getRootA() {
        return rootA;
    }

    public void setRootA(String rootA) {
        this.rootA = rootA;
    }

    public String getRootB() {
        return rootB;
    }

    public void setRootB(String rootB) {
        this.rootB = rootB;
    }
}
