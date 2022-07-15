package com.project.application.service;

import com.project.application.entity.Ticket;

import java.util.List;

public interface TicketService
{

    public List<Ticket> getAllTickets();

    public Ticket getTicket(int id);

    public List<Ticket> getTicketsAssignedTo(String username);

    public List<Ticket> getTicketsAssignedBy(String username);

    public List<String> getStatusList();

    public void saveTicket(Ticket ticket);

    public void deleteTicket(int id);
}
