package com.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XmlTest {


    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XmlBody.class);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ClassA classA = new ClassA();
        classA.setEleA("eleA");
        classA.setEleB("eleB");
        classA.setAttrC("attrC");

        ClassB classB = new ClassB();
        classB.setAttrUserName("attrA");
        classB.setAttrPassword("attrB");
        classB.setEleCode("eleC");

        XmlBody xmlBody =new XmlBody();
        xmlBody.setClassA(classA);
        xmlBody.setClassB(classB);
        xmlBody.setRootA("rootA");
        xmlBody.setRootB("rootB");
        marshaller.marshal(xmlBody,System.out);
        System.out.println("\n");

//        String xml = "<xmlBody><name>David</name></xmlBody>";
//        XmlBody xmlBody1 = (XmlBody) unmarshaller.unmarshal(new StringReader(xml));
//        System.out.println(xmlBody1);
    }
}
