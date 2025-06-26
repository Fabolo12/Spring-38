package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class JoinPointAspect {
    @Before("execution(public void com.example.demo.models..set*(*))")
    public void loggingAdvice(JoinPoint joinPoint) {
        System.out.println("[JoinPointAspect]: Before running loggingAdvice on method=" + joinPoint.toString());

        System.out.println("[JoinPointAspect]: Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));

    }

    //Advice arguments, will be applied to bean methods with single String argument
    @Before(value = "execution(public void com.example.demo.models..set*(*)) && args(name)", argNames = "name")
    public void logStringArguments(String name) {
        System.out.println("[JoinPointAspect]: String argument passed=" + name);
    }
}
