package com.project.application.service;

import com.project.application.dao.TicketRepository;
import com.project.application.entity.Ticket;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestTicketService
{
    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketService ticketService = new TicketServiceImplementation();


    @Test
    void testGetAllTickets()
    {
        //given
        List<Ticket> ticketList = new ArrayList<>();

        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        Ticket ticket1 = new Ticket(1, "Learn Java", user2, user1, "To Do");
        Ticket ticket2 = new Ticket(2, "Learn Spring", user2, user1, "To Do");

        ticketList.add(ticket1);
        ticketList.add(ticket2);

        //when
        when(ticketRepository.findAll()).thenReturn(ticketList);

        List<Ticket> ticketsFetched = ticketService.getAllTickets();

        //test
        assertEquals(2, ticketsFetched.size());
        assertEquals("Learn Java", ticketsFetched.get(0).getTicketName());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testGetTicket()
    {
        //given
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        Ticket myTicket = new Ticket(1, "Learn Java", user2, user1, "To Do");

        //when
        when(ticketRepository.findById(1)).thenReturn(Optional.of(myTicket));

        Ticket fetchedTicket = ticketService.getTicket(1);

        //test
        assertEquals(fetchedTicket.getTicketId(), myTicket.getTicketId());

    }

    @Test
    void testGetTicketsAssignedTo()
    {
        //given
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        Ticket myTicket = new Ticket(1, "Learn Java", user2, user1, "To Do");

        List<Ticket> ticketsAssignedToSuresh = new ArrayList<>();
        ticketsAssignedToSuresh.add(myTicket);

        //when
        when(ticketRepository.findByAssignedToUserName("Suresh")).thenReturn(ticketsAssignedToSuresh);

        List<Ticket> fetchedList = ticketService.getTicketsAssignedTo("Suresh");

        //test
        assertEquals(ticketsAssignedToSuresh.get(0).getTicketName(), fetchedList.get(0).getTicketName());

    }


    @Test
    void testGetTicketsAssignedBy()
    {
        //given
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        Ticket myTicket = new Ticket(1, "Learn Java", user2, user1, "To Do");

        List<Ticket> ticketsAssignedByMahesh = new ArrayList<>();
        ticketsAssignedByMahesh.add(myTicket);

        //when
        when(ticketRepository.findByAssignedByUserName("Mahesh")).thenReturn(ticketsAssignedByMahesh);

        List<Ticket> fetchedList = ticketService.getTicketsAssignedBy("Mahesh");

        //test
        assertEquals(ticketsAssignedByMahesh.get(0).getTicketName(), fetchedList.get(0).getTicketName());

    }

    @Test
    void testGetStatusList()
    {
        //when
        List<String> statusList = ticketService.getStatusList();

        assertThat(statusList, hasItem("To Do"));
    }

    @Test
    void testSaveTicket()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        Ticket myTicket = new Ticket(1, "Learn Java", user2, user1, "To Do");

        when(ticketRepository.save(myTicket)).thenReturn(myTicket);

        ticketService.saveTicket(myTicket);

        verify(ticketRepository).save(myTicket);
    }

    @Test
    void testDeleteTicket()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        Ticket myTicket = new Ticket(1, "Learn Java", user2, user1, "To Do");

        doNothing().when(ticketRepository).deleteById(1);

        ticketService.deleteTicket(myTicket.getTicketId());

        verify(ticketRepository).deleteById(1);
    }
}
