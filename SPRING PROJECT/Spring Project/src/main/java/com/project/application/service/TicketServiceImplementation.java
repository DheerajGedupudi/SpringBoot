package com.project.application.service;

import com.project.application.dao.TicketRepository;
import com.project.application.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService
{
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets()
    {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(int id)
    {
        Optional<Ticket> optionalTicket =  ticketRepository.findById(id);

        Ticket myTicket = null;

        if (optionalTicket.isPresent())
        {
            myTicket = optionalTicket.get();
        }

        return myTicket;
    }

    @Override
    public List<Ticket> getTicketsAssignedTo(String username)
    {
        return ticketRepository.findByAssignedToUserName(username);
    }


    @Override
    public List<Ticket> getTicketsAssignedBy(String username)
    {
        return ticketRepository.findByAssignedByUserName(username);
    }

    @Override
    public List<String> getStatusList()
    {
        List<String> list = new ArrayList<>();
        list.add("To Do");
        list.add("In Progress");
        list.add("Done");

        return list;
    }

    @Override
    public void saveTicket(Ticket ticket)
    {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(int id)
    {
        ticketRepository.deleteById(id);
    }


}
