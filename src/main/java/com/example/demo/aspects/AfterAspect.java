package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AfterAspect {
    @After("execution(public void com.example.demo.models..set*(*))")
    public void logStringArguments(JoinPoint joinPoint) {
        System.out.println("[AfterAspect]: String argument passed=" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing("within(com.example.demo.models.User)")
    public void logExceptions(JoinPoint joinPoint) {
        System.out.println("[AfterAspect]: Exception thrown in User Method=" + joinPoint.toString());
    }

    @AfterReturning(pointcut = "execution(* getName())", returning = "returnString")
    public void getNameReturningAdvice(String returnString) {
        System.out.println("[AfterAspect]: Returned String=" + returnString);
    }
}
