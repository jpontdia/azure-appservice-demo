package com.demo.creditservices.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@SuppressWarnings("unused")
public class LoggingConfiguration {

    @Pointcut("within(com.demo..*)")
    public void globalPointCut(){
        //Empty method
    }

    @Around("globalPointCut()")
    public Object logger(ProceedingJoinPoint pjp) throws Throwable{
        var methodName = pjp.getSignature().getName();
        var className = pjp.getTarget().getClass().getName();
        Object[] arguments = pjp.getArgs();
        log.debug("Invoked: {}.{}, input: {}", className, methodName, Arrays.toString(arguments));
        var point = pjp.proceed();
        log.debug("Response: {}, output: {}", methodName, point);
        return point;
    }
}
