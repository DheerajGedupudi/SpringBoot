package com.practice.crud.service;

import com.practice.crud.dao.EmployeeDAO;
import com.practice.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService
{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImplementation(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Employee myEmployee) {
        employeeDAO.save(myEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}
