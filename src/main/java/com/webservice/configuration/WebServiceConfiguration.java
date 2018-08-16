package com.webservice.configuration;

import com.webservice.service.impl.GreetingServiceImpl;
import com.webservice.service.impl.InforServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author zhangjun
 */
@Configuration
public class WebServiceConfiguration {
    @Bean
    ServletRegistrationBean dispatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(), "/services");
    }

    @Bean
    Endpoint endpointGreetingService(){
        EndpointImpl endpoint = new EndpointImpl(springBus(),new GreetingServiceImpl());
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/GreetingService");
        return endpoint;
    }

    @Bean
    Endpoint endpointInfoService() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new InforServiceImpl());
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/InfoService");
        return endpoint;
    }

    @Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        return springBus;
    }
}
