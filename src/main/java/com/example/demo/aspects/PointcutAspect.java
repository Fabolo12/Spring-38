package com.example.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointcutAspect {
    @Before("getNamePointcut()")
    public void loggingAdvice() {
        System.out.println("[PointcutAspect]: Executing loggingAdvice on getName()");
    }

    @Before("getNamePointcut()")
    public void secondAdvice() {
        System.out.println("[PointcutAspect]: Executing secondAdvice on getName()");
    }

    @Pointcut("execution(public String getName())")
    public void getNamePointcut() {
    }

    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice() {
        System.out.println("[PointcutAspect]: Before executing service method");
    }

    //Pointcut to execute on all the methods of classes in a package
    @Pointcut("within(com.example.demo.models.*)")
    public void allMethodsPointcut() {
    }
}
