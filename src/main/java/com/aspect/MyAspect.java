package com.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author someone
 */
@Aspect
@Component
public class MyAspect {

    Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @Pointcut("execution(public * com.controller.*.*(..))")
    void testAspect() {
    }

    @Before("testAspect()")
    void testBefore() {
        logger.info("test before");
    }

    @After("testAspect()")
    void testAfter() {
        logger.info("test after");
    }

    @AfterReturning("testAspect()")
    void doAfterReturning() {
        logger.info("After returning");
    }

    @AfterThrowing("testAspect()")
    void doAfterThrows() {
        logger.error("after throws");
    }

    @Around("testAspect()")
    public Object testAround(ProceedingJoinPoint pjp) {
        logger.info("test Around");
        Object o = null;
        try {
            o = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }


}
