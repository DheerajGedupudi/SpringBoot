package com.practice.thymeleafdemo.service;

import com.practice.thymeleafdemo.dao.EmployeeRepository;
import com.practice.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService
{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee findById(int id)
    {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee myEmployee = null;

        if (result.isPresent())
        {
            myEmployee = result.get();
        }
        else
        {
            throw new RuntimeException("Did not find employee id : "+id);
        }

        return myEmployee;
    }

    @Override
    public void save(Employee myEmployee) {
        employeeRepository.save(myEmployee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
