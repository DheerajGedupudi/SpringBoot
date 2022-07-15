package com.project.application.controller;

import com.project.application.entity.Ticket;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import com.project.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class EmployeeController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/list")
    public String listTheEmployees(Model myModel)
    {
        List<User> details = userService.getOnlyEmployees();

        myModel.addAttribute("employees", details);

        return "list-users";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model myModel)
    {
        UserDetail myDetail = new UserDetail();

        myModel.addAttribute("employee", myDetail);

        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") User myDetail)
    {
        userService.saveUser(myDetail);

        return "redirect:/manager/list";
    }


    @GetMapping("/promote/{username}")
    public String promoteEmployee(@PathVariable String username)
    {
        User user = userService.getUserInfo(username);

        authorityService.makeManagerAuthority(user);

        return "redirect:/manager/list";
    }

    @GetMapping("/showFormToUpdate")
    public String showFormToUpdate(@RequestParam("employeeId") String username, Model myModel)
    {
        User myDetail = userService.getUser(username);

        myModel.addAttribute("employee", myDetail);

        return "employee-form";
    }

    @GetMapping("/delete/{username}")
    public String delete(@PathVariable String username, Principal myPrincipal, Model myModel)
    {
        String currentUsername = myPrincipal.getName();
        if (currentUsername.equals(username))
        {
            return "cannot-delete-user";
        }

        List<Ticket> ticketsAssigned = ticketService.getTicketsAssignedTo(username);

        if (ticketsAssigned.size()>0)
        {
            myModel.addAttribute("myUser", username);
            return "cannot-delete-user-tickets-present";
        }

        UserDetail myUserDetail = userService.getUserDetailId(username);
        User myUser = userService.getUser(username);
        //delete
        authorityService.deleteAllAuthoritiesOf(myUser);
        userService.deleteUser(myUser);
        userDetailService.deleteDetail(myUserDetail);


        return "redirect:/manager/list";
    }
}
