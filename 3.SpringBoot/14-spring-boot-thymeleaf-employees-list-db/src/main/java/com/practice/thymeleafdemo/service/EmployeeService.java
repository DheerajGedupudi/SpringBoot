package com.practice.thymeleafdemo.service;


import com.practice.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeService
{
    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee myEmployee);

    public void deleteById(int id);

}
