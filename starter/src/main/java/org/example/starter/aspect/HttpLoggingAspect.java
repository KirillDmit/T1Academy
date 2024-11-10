package org.example.starter.aspect;

import org.example.starter.config.HttpLoggingProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Aspect
@Component
public class HttpLoggingAspect {

    private final HttpLoggingProperties properties;

    public HttpLoggingAspect(HttpLoggingProperties properties) {
        this.properties = properties;
    }

    @Before("@within(restController) && @annotation(requestMapping)")
    public void logRequest(JoinPoint joinPoint, RestController restController, RequestMapping requestMapping) {
        if (!properties.isEnabled()) return;

        log.info("HTTP Request - {}", joinPoint.getSignature().toShortString());
        if ("HEADERS".equalsIgnoreCase(properties.getLevel()) || "FULL".equalsIgnoreCase(properties.getLevel())) {
            log.info("Headers: ...");
        }
    }

    @AfterReturning(pointcut = "@within(restController) && @annotation(requestMapping)", returning = "result")
    public void logResponse(JoinPoint joinPoint, RestController restController, RequestMapping requestMapping, Object result) {
        if (!properties.isEnabled()) return;

        log.info("HTTP Response - {}", result);
        if ("FULL".equalsIgnoreCase(properties.getLevel())) {
            log.info("Response details: ...");
        }
    }
}