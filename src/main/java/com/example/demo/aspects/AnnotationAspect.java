package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AnnotationAspect {
    @Around("@annotation(com.example.demo.aspects.Loggable)")
    public Object myAdvice(ProceedingJoinPoint call) {
        final int start = LocalTime.now().getSecond();

        Object value = null;
        try {
            TimeUnit.SECONDS.sleep(0);
            value = call.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        final int end = LocalTime.now().getSecond();

        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();
        Loggable annotation = method.getAnnotation(Loggable.class);
        final long maxTime = annotation.maxTime();
        final long currentTime = Duration.between(LocalTime.of(0, 0, start), LocalTime.of(0, 0, end)).getSeconds();
        if (currentTime >= maxTime) {
            System.out.println("[AnnotationAspect]: Method " + method.getName() + " took too long to execute: "
                    + currentTime + " seconds, exceeding the max time of " + maxTime + " seconds.");
        } else {
            System.out.println("[AnnotationAspect]: Method " + method.getName() + " executed successfully in "
                    + currentTime + " seconds.");
        }
        return value;
    }
}
