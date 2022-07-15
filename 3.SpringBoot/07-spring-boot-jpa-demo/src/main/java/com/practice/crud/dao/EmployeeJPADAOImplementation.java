package com.practice.crud.dao;

import com.practice.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeJPADAOImplementation implements EmployeeDAO
{
    private EntityManager entityManager;

    @Autowired
    public EmployeeJPADAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll()
    {
        Query myQuery = entityManager.createQuery("from Employee");

        List<Employee> employees = myQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int theId)
    {
        Employee myEmployee = entityManager.find(Employee.class, theId);
        {
            return myEmployee;
        }
    }

    @Override
    public void save(Employee theEmployee)
    {
        Employee myEmployee = entityManager.merge(theEmployee);

        theEmployee.setId(myEmployee.getId());
    }

    @Override
    public void deleteById(int theId)
    {
        Query myQuery = entityManager.createQuery("delete from Employee where id=:empId");

        myQuery.setParameter("empId", theId);

        myQuery.executeUpdate();
    }
}
