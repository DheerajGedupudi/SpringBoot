package com.practice.thymeleafdemo.controller;

import com.practice.thymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController
{
    private List<Employee> employees;

    @PostConstruct
    private void loadData()
    {
        Employee emp1 = new Employee(1, "Suresh", "Kumar", "suresh@gmail.com");
        Employee emp2 = new Employee(2, "Ramesh", "Rao", "ramesh@gmail.com");
        Employee emp3 = new Employee(3, "Mahesh", "Reddy", "mahesh@gmail.com");

        employees = new ArrayList<>();

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
    }

    @GetMapping("/list")
    public String listTheEmployees(Model myModel)
    {
        myModel.addAttribute("employees", employees);

        return "list-employees";
    }
}
