package com.project.application.controller;

import com.project.application.dto.NewManager;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import com.project.application.service.AuthorityService;
import com.project.application.service.UserDetailService;
import com.project.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/manager/managers")
public class ManagerController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/list")
    public String listTheEmployees(Model myModel)
    {
        List<User> details = userService.getManagers();

        myModel.addAttribute("employees", details);

        return "list-managers";
    }

    @GetMapping("/makeManager")
    public String showFormToAdd(Model myModel)
    {
        List<User> employeeList = userService.getOnlyEmployees();

        NewManager newManager = new NewManager();

        myModel.addAttribute("employeeList", employeeList);

        myModel.addAttribute("newManager", newManager);

        return "make-employee-manager";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employeeName") NewManager newManager)
    {
        User user = userService.getUserInfo(newManager.getManagerUsername());

        authorityService.makeManagerAuthority(user);

        return "redirect:/manager/managers/list";
    }


    @GetMapping("/demote/{username}")
    public String demoteManager(@PathVariable String username, Principal myPrincipal)
    {
        String currentUsername = myPrincipal.getName();
        if (currentUsername.equals(username))
        {
            return "cannot-demote-user";
        }
        User user = userService.getUserInfo(username);

        authorityService.removeManagerAuthority(user);

        return "redirect:/manager/managers/list";
    }

    @GetMapping("/showFormToUpdate")
    public String showFormToUpdate(@RequestParam("employeeId") String username, Model myModel)
    {
        User myDetail = userService.getUser(username);

        myModel.addAttribute("employee", myDetail);

        return "employee-form";
    }

    @GetMapping("/delete/{username}")
    public String delete(@PathVariable String username, Principal myPrincipal)
    {
        String currentUsername = myPrincipal.getName();
        if (currentUsername.equals(username))
        {
            return "cannot-delete-user";
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
