package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAspect {
    @Around("execution(* com.example.demo.models.User.getName())")
    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("[AroundAspect]: Before invoking getName() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("[AroundAspect]: After invoking getName() method. Return value=" + value);
        return "***" + value + "***";
    }
}
