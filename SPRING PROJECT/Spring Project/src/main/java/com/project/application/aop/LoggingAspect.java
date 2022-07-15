package com.project.application.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect
{
    Logger myLogger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(public * com.project.application.controller.TicketController.showFormToAdd(..))")
    public void beforeAdding(){}


    @Pointcut("execution(public * com.project.application.controller.TicketController.saveTicket(..))")
    public void afterSaving(){
    }

    @Before("com.project.application.aop.LoggingAspect.beforeAdding()")
    public void logAddTicket()
    {
        myLogger.info("\n\n\nNew Ticket being Added\n\n");
    }


    @After("com.project.application.aop.LoggingAspect.afterSaving()")
    public void logSaveTicket()
    {
        myLogger.info("\n\n\nNew Ticket is Saved\n\n");
    }
}
