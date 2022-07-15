package com.practice.thymeleafdemo.dao;

import com.practice.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
