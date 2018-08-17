package com.webservice.configuration;

import com.webservice.service.impl.GreetingServiceImpl;
import com.webservice.service.impl.InforServiceImpl;
import com.webservice.service.interceptor.AppInboundInterceptor;
import com.webservice.service.interceptor.AppOutboundInterceptor;
import com.webservice.service.interceptor.AuthInterceptor;
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
    ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        springBus.getInInterceptors().add(new AuthInterceptor());
//        springBus.getInInterceptors().add(new AppInboundInterceptor());
//        springBus.getOutInterceptors().add(new AppOutboundInterceptor());
        return springBus;
    }

    @Bean
    Endpoint GreetingService() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new GreetingServiceImpl());
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/GreetingService");
        return endpoint;
    }

    @Bean
    Endpoint InfoService() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new InforServiceImpl());
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/InfoService");
        return endpoint;
    }
}
