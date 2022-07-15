package com.project.application.entity;


import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int ticketId;

    @Column(name = "ticket_name")
    private String ticketName;

    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedByUser;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedToUser;

    @Column(name = "status")
    private String status;

    public Ticket() {
    }

    public Ticket(int ticketId, String ticketName, User assignedByUser, User assignedToUser, String status) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.assignedByUser = assignedByUser;
        this.assignedToUser = assignedToUser;
        this.status = status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public User getAssignedByUser() {
        return assignedByUser;
    }

    public void setAssignedByUser(User assignedByUser) {
        this.assignedByUser = assignedByUser;
    }

    public User getAssignedToUser() {
        return assignedToUser;
    }

    public void setAssignedToUser(User assignedToUser) {
        this.assignedToUser = assignedToUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", ticketName='" + ticketName + '\'' +
                ", assignedByUser=" + assignedByUser +
                ", assignedToUser=" + assignedToUser +
                ", status='" + status + '\'' +
                '}';
    }
}
