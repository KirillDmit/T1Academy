package org.example.advice;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.service.MyService.someMethod(..))")
    public void beforeAdvice() {
        System.out.println("Before method execution");
    }

    @AfterThrowing(pointcut = "execution(* org.example.service.MyService.someMethod(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        System.out.println("After throwing exception: " + exception.getMessage());
    }

    @AfterReturning(pointcut = "execution(* org.example.service.MyService.someMethod(..))", returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("After method returns with result: " + result);
    }

    @Around("execution(* org.example.service.MyService.someMethod(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around advice: before proceeding");
        Object result = joinPoint.proceed();
        System.out.println("Around advice: after proceeding");
        return result;
    }
}

