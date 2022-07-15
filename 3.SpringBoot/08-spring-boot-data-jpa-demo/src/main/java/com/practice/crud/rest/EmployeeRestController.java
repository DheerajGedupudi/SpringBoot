package com.practice.crud.rest;

import com.practice.crud.entity.Employee;
import com.practice.crud.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController
{
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id)
    {
        Employee myEmployee = employeeService.findById(id);

        if (myEmployee==null)
        {
            throw new RuntimeException("Employee id not found : "+id);
        }
        return myEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee myEmployee)
    {
        myEmployee.setId(0);

        employeeService.save(myEmployee);

        return myEmployee;
    }

    @PutMapping("/employees")
    public Employee updateTheEmployee(@RequestBody Employee myEmployee)
    {
        employeeService.save(myEmployee);

        return myEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteTheEmployee(@PathVariable int id)
    {
        Employee myEmployee = employeeService.findById(id);

        if (myEmployee==null)
        {
            throw new RuntimeException("Employee is not found : "+id);
        }

        employeeService.deleteById(id);

        return "Deleted employee with id : "+id;
    }
}
