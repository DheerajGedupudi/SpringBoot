package com.practice.crud.service;

import com.practice.crud.entity.Employee;

import java.util.List;

public interface EmployeeService
{
    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee myEmployee);

    public void deleteById(int id);

}
