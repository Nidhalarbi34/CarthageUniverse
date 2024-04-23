package tn.enicarthage.EnicarthageUniverse.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* tn.enicarthage.EnicarthageUniverse.services.EmailSenderService.*(..))")
    public void emailServiceMethods() {}

    @After("emailServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("After method: " + joinPoint.getSignature().getName());}
    @Pointcut("execution(* tn.enicarthage.EnicarthageUniverse.services.NotificationService.*(..))")
    public void notificationServiceMethods() {}

    // Advice to log before each method execution
    @Before("notificationServiceMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    // Advice to log after each method execution
    @After("notificationServiceMethods()")
    public void logMethodExit(JoinPoint joinPoint) {
        logger.info("Exiting method: {}", joinPoint.getSignature().getName());
    }

}