package com.project.application.aop;

import com.project.application.entity.Ticket;
import com.project.application.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Aspect
@Component
@Slf4j
public class LoggingAspect
{
    @Autowired
    TicketService ticketService;

    @Pointcut("execution(public * com.project.application.controller.TicketController.showFormToAdd(..))")
    public void beforeAdding(){}

    @Pointcut("execution(public * com.project.application.controller.TicketController.saveTicket(..))")
    public void afterSaving(){}

    @Pointcut("execution(public * com.project.application.controller.HomeController.saveTicket(..))")
    public void afterEmployeeTicketUpdate(){}

    @Pointcut("execution(public * com.project.application.controller.TicketController.saveUpdatedTicket(..))")
    public void afterManagerTicketUpdate(){}

    @Pointcut("execution(public * com.project.application.controller.TicketController.deleteTicket(..))")
    public void beforeDeleteTicket(){}


    @Before("com.project.application.aop.LoggingAspect.beforeAdding()")
    public void logAddTicket(JoinPoint myJoinPoint)
    {
        Object myArgs[] = myJoinPoint.getArgs();
        Principal currentUser = (Principal) myArgs[1];

        log.info("\nLogging using slf4j : New Ticket Form is opened by Manager - "+currentUser.getName()+"\n");
    }

    @After("com.project.application.aop.LoggingAspect.afterSaving()")
    public void logSaveTicket(JoinPoint myJoinPoint)
    {
        Object myArgs[] = myJoinPoint.getArgs();
        Ticket updatedTicket = (Ticket) myArgs[0];
        Principal currentUser = (Principal) myArgs[1];

        log.info("\nLogging using slf4j : New Ticket is saved by Manager - "+currentUser.getName());
        log.info("\nLogging using slf4j : New ticket details - "+updatedTicket+"\n");
    }


    @After("com.project.application.aop.LoggingAspect.afterEmployeeTicketUpdate()")
    public void logEmployeeTicketUpdate(JoinPoint myJoinPoint)
    {
        Object myArgs[] = myJoinPoint.getArgs();
        Ticket updatedTicket = (Ticket) myArgs[0];
        Principal currentUser = (Principal) myArgs[1];

        log.info("\nLogging using slf4j : Ticket is updated by Employee - "+currentUser.getName());
        log.info("\nLogging using slf4j : Ticket Name - "+updatedTicket.getTicketName()+", Status is changed to - "+updatedTicket.getStatus()+"\n");
    }

    @After("com.project.application.aop.LoggingAspect.afterManagerTicketUpdate()")
    public void logManagerTicketUpdate(JoinPoint myJoinPoint)
    {
        Object myArgs[] = myJoinPoint.getArgs();
        Ticket updatedTicket = (Ticket) myArgs[0];
        Principal currentUser = (Principal) myArgs[1];

        log.info("\nLogging using slf4j : Ticket is updated by Manager - "+currentUser.getName());
        log.info("\nLogging using slf4j : Updated ticket details - "+updatedTicket+"\n");
    }

    @Before("com.project.application.aop.LoggingAspect.beforeDeleteTicket()")
    public void logManagerTicketDelete(JoinPoint myJoinPoint)
    {
        Object myArgs[] = myJoinPoint.getArgs();
        int ticketId = (int)myArgs[0];
        Ticket deletingTicket = ticketService.getTicket(ticketId);
        Principal currentUser = (Principal) myArgs[1];

        log.info("\nLogging using slf4j : Ticket is deleted by Manager - "+currentUser.getName());
        log.info("\nLogging using slf4j : Deleted ticket details - "+deletingTicket+"\n");
    }
}
