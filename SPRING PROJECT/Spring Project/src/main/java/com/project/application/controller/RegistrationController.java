package com.project.application.controller;

import com.project.application.dto.UserDTO;
import com.project.application.entity.Authority;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import com.project.application.entity.id.AuthorityId;
import com.project.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager/users")
public class RegistrationController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthorityService authorityService;


    @GetMapping("/registrationManager")
    public String showRegistrationFormForManager(Model myModel)
    {
        UserDTO userDTO = new UserDTO();

        myModel.addAttribute("userDTO", userDTO);

        return "manager-registration";
    }

    @PostMapping("/saveManager")
    public String saveManager(@ModelAttribute("employee") UserDTO userDTO)
    {
        //making userDetail entity
        UserDetail userDetail = new UserDetail(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        userDetailService.saveDetail(userDetail);
        //making User entity
        String password = userDTO.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encrypted = "{bcrypt}"+passwordEncoder.encode(password);
        User myUser = new User(userDTO.getUserName(), encrypted, 1, userDetail);
        userService.saveUser(myUser);
        //adding relevant authorities
        String authority = "ROLE_EMPLOYEE";
        AuthorityId employeeAuthorityId = new AuthorityId(myUser, authority);
        Authority employeeAuthority = new Authority(employeeAuthorityId);
        authorityService.saveAuthority(employeeAuthority);

        AuthorityId managerAuthorityId = new AuthorityId(myUser, "ROLE_MANAGER");
        Authority managerAuthority = new Authority(managerAuthorityId);
        authorityService.saveAuthority(managerAuthority);

        return "redirect:/manager/list";
    }

    @GetMapping("/registrationEmployee")
    public String showRegistrationFormForEmployee(Model myModel)
    {
        UserDTO userDTO = new UserDTO();

        myModel.addAttribute("userDTO", userDTO);

        return "employee-registration";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") @Valid UserDTO userDTO)
    {
        //making userDetail entity
        UserDetail userDetail = new UserDetail(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        userDetailService.saveDetail(userDetail);

        //making User entity
        String password = userDTO.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encrypted = "{bcrypt}"+passwordEncoder.encode(password);
        User myUser = new User(userDTO.getUserName(), encrypted, 1, userDetail);
        userService.saveUser(myUser);

        //adding relevant authorities
        String authority = "ROLE_EMPLOYEE";
        AuthorityId employeeAuthorityId = new AuthorityId(myUser, authority);
        Authority employeeAuthority = new Authority(employeeAuthorityId);
        authorityService.saveAuthority(employeeAuthority);

        return "redirect:/manager/list";
    }
}
