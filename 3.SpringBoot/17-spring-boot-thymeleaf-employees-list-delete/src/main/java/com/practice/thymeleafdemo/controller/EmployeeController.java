package com.practice.thymeleafdemo.controller;

import com.practice.thymeleafdemo.entity.Employee;
import com.practice.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormToUpdate")
    public String showFormToUpdate(@RequestParam("employeeId") int id, Model myModel)
    {
        System.out.println("\n\n\nReached here to update");
        Employee myEmployee = employeeService.findById(id);

        myModel.addAttribute("employee", myEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id)
    {
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
