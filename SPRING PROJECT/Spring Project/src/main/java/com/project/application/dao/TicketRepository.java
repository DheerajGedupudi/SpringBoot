package com.project.application.dao;

import com.project.application.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>
{
    public List<Ticket> findByTicketName(String name);

    @Transactional
    @Query("from Ticket t where t.assignedToUser.userName = :username")
    public List<Ticket> findByAssignedToUserName(String username);


    @Transactional
    @Query("from Ticket t where t.assignedByUser.userName = :username")
    public List<Ticket> findByAssignedByUserName(String username);
}
