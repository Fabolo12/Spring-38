package com.example.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAspect {
    @Before("execution(public String getName())")
    public void getNameAdvice() {
        System.out.println("[BeforeAspect]: Executing Advice on getName()");
    }

    @Before("execution(public String getEmail())")
    public void getEmailAdvice() {
        System.out.println("[BeforeAspect]: Executing Advice on getEmail()");
    }

    @Before("execution(* com.example.demo.models.*.get*())")
    public void getAllAdvice(){
        System.out.println("[BeforeAspect]: Service method getter called");
    }
}
