package com.viajaYa.viajaYa.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RequestLoggingAspect {

    private final ApplicationLogger logger = ApplicationLogger.getInstance();//uso del singleton

    private final HttpServletRequest request;

    public RequestLoggingAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("execution(* com.viajaYa.viajaYa.controllers..*(..))")
    public void logRequestDetails(JoinPoint joinPoint) {
        String url = request.getRequestURI();
        String method = request.getMethod();
        String params = Arrays.toString(joinPoint.getArgs());

        logger.getLogger().info("Request: URL={}, Method={}, Params={}", url, method, params);
    }
}
