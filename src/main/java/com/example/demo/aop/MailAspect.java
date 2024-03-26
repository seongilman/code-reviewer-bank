package com.example.demo.aop;

import com.example.demo.mail.MailQueueWatcher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MailAspect {
    @Autowired
    MailQueueWatcher mailQueueWatcher;

    @Around("execution(* com.example.demo.service.ProductService.createProduct(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around before: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        mailQueueWatcher.getMailQueue().add(String.valueOf(result));
        log.info("Around after: " + joinPoint.getSignature().getName());
        return result;
    }
}
