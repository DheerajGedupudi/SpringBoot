package com.practice.crud.dao;

import com.practice.crud.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImplementation implements EmployeeDAO
{
    //field for entityManager
    private EntityManager entityManager;

    //const injection
    @Autowired
    public EmployeeDAOHibernateImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll()
    {
        Session session = entityManager.unwrap(Session.class);

        Query<Employee> myQuery = session.createQuery("from Employee", Employee.class);

        List<Employee> employees = myQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Employee theEmployee =
                currentSession.get(Employee.class, theId);

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theEmployee);
    }


    @Override
    public void deleteById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery(
                        "delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();
    }

}
