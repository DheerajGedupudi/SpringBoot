package com.practice.thymeleafdemo.controller;

import com.practice.thymeleafdemo.entity.Employee;
import com.practice.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController
{
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listTheEmployees(Model myModel)
    {
        List<Employee> employees = employeeService.findAll();

        myModel.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model myModel)
    {
        Employee myEmployee = new Employee();

        myModel.addAttribute("employee", myEmployee);

        System.out.println("Now, employee-form will be loaded");
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee myEmployee)
    {
        employeeService.save(myEmployee);

        return "redirect:/employees/list";
    }
}
