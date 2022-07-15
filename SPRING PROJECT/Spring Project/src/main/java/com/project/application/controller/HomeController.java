package com.project.application.controller;

import com.project.application.entity.Ticket;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import com.project.application.service.AuthorityService;
import com.project.application.service.TicketService;
import com.project.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    private String ticketName;
    private User assignedByUser;
    private User assignedToUser;

    @GetMapping("/")
    public String sayHello(Model myModel, Principal myPrincipal)
    {

        User myUser = userService.getUser(myPrincipal.getName());

        UserDetail myUserDetail = userService.getUserDetailId(myUser.getUserName());

        String welcomeStatement = myUserDetail.getFirstName()+" "+myUserDetail.getLastName();

        List<Ticket> ticketList = ticketService.getTicketsAssignedTo(myPrincipal.getName());

        List<String> statusList = ticketService.getStatusList();

        myModel.addAttribute("ticketList", ticketList);
        myModel.addAttribute("statusList", statusList);
        myModel.addAttribute("welcomeStatement", welcomeStatement);

        return "index";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage()
    {
        return "cannot-view-manager";
    }

    @GetMapping("/update/{id}")
    public String modifyStatusOfTicket(@PathVariable int id, Model myModel)
    {
        Ticket myTicket = ticketService.getTicket(id);

        ticketName = myTicket.getTicketName();
        assignedByUser = myTicket.getAssignedByUser();
        assignedToUser = myTicket.getAssignedToUser();


        List<String> statusList = ticketService.getStatusList();

        List<User> userList = userService.getUsers();

        myModel.addAttribute("myTicket", myTicket);
        myModel.addAttribute("users", userList);
        myModel.addAttribute("statusList", statusList);

        return "employee-update-ticket";
    }

    @PostMapping("/update/save")
    public String saveTicket(@ModelAttribute("employee") Ticket myTicket)
    {
        myTicket.setTicketName(ticketName);
        myTicket.setAssignedByUser(assignedByUser);
        myTicket.setAssignedToUser(assignedToUser);

        ticketService.saveTicket(myTicket);

        return "redirect:/";
    }

    @GetMapping("/manager")
    public String goToManagerPage()
    {
        return "redirect:/";
    }
}
