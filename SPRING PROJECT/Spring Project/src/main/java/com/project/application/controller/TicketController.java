package com.project.application.controller;


import com.project.application.entity.Ticket;
import com.project.application.entity.User;
import com.project.application.service.TicketService;
import com.project.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/manager/tickets")
public class TicketController
{

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public String listAllTickets(Model myModel)
    {
        List<Ticket> tickets = ticketService.getAllTickets();

        myModel.addAttribute("ticketList", tickets);

        return "list-tickets";
    }

    @GetMapping("/list/{username}")
    public String listTicketsOfId(@PathVariable String username, Model myModel)
    {
        List<Ticket> ticketList = ticketService.getTicketsAssignedTo(username);

        myModel.addAttribute("ticketList", ticketList);

        return "list-tickets";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model myModel, Principal myPrincipal)
    {
        List<User> userList = userService.getUsers();

        String currentUser = myPrincipal.getName();

        Ticket myTicket = new Ticket();

        List<String> statusList = ticketService.getStatusList();

        myModel.addAttribute("ticket", myTicket);
        myModel.addAttribute("users", userList);
        myModel.addAttribute("currentUser", currentUser);
        myModel.addAttribute("statusList", statusList);

        return "ticket-form";
    }

    @PostMapping("/save")
    public String saveTicket(@ModelAttribute("employee") Ticket myTicket, Principal myPrincipal)
    {
        String username = myPrincipal.getName();

        myTicket.setAssignedByUser(userService.getUserInfo(username));

        ticketService.saveTicket(myTicket);

        return "redirect:/manager/tickets/list";
    }

    @PostMapping("/saveUpdated")
    public String saveUpdatedTicket(@ModelAttribute("employee") Ticket myTicket, Principal myPrincipal)
    {
        String username = myPrincipal.getName();

        ticketService.saveTicket(myTicket);

        return "redirect:/manager/tickets/list";
    }

    @GetMapping("/showFormToUpdate/{id}")
    public String showFormToUpdate(@PathVariable int id, Model myModel)
    {
        Ticket myTicket = ticketService.getTicket(id);

        List<User> userList = userService.getUsers();

        List<User> managerList = userService.getManagers();

        List<String> statusList = ticketService.getStatusList();

        myModel.addAttribute("myTicket", myTicket);
        myModel.addAttribute("users", userList);
        myModel.addAttribute("managers", managerList);
        myModel.addAttribute("statusList", statusList);

        return "ticket-update-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable int id, Principal myPrincipal)
    {
        ticketService.deleteTicket(id);

        return "redirect:/manager/tickets/list";
    }
}
