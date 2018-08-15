package com.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author someone
 */
//@Configuration
public class ConfigConfiguration {

    @ConditionalOnClass(Abc.class)
    @Bean
    public String abc() {
        System.err.println("ConditionalOnClass true");
        return "";
    }

   /* @Bean
    public Abc getInstance(){
        return new Abc();
    }*/

    @ConditionalOnBean(Abc.class)
    @Bean
    public String bean() {
        System.err.println("ConditionalOnBean is exist");
        return "";
    }

    @ConditionalOnMissingBean(Abc.class)
    @Bean
    public String missBean() {
        System.err.println("ConditionalOnBean is missing");
        return "";
    }

    @ConditionalOnWebApplication
    @Bean
    public String webApplication() {
        System.err.println("ConditionalOnWebApplication is exist");
        return "";
    }

    @Conditional(MyConditional.class)
    @Bean
    public String myConditional(){
        System.err.println("MyConditional is exist");
        return "";
    }
}
