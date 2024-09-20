package com.ennovtest.ticket.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ennovtest.ticket.DTOs.TicketDto;
import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.TicketStatus;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Repositories.TicketRepository;
import com.ennovtest.ticket.Repositories.UserRepository;
import com.ennovtest.ticket.Services.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testCreateTicket(){
        TicketDto ticketDto = new TicketDto("Ticket Test 1", "Ceci est un ticket test", TicketStatus.CANCELLED);
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.CANCELLED.toString());
        ticket.setTitle("Ticket Test 1");
        ticket.setDescription("Ceci est un ticket test");

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket createdTicket = ticketService.save(ticketDto);

        verify(ticketRepository).save(any(Ticket.class));
        assertEquals(ticket.getTitle(), createdTicket.getTitle());
        assertEquals(ticket.getStatus(), createdTicket.getStatus());

    }

    @Test
    public void testFindTickets(){
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticketA = new Ticket();
        ticketA.setTitle("Test Ticket A");
        ticketA.setDescription("Ceci est le ticket test A");
        ticketA.setStatus(TicketStatus.IN_PROGRESS.toString());

        Ticket ticketB = new Ticket();
        ticketB.setTitle("Test Ticket B");
        ticketB.setDescription("Ceci est le ticket test B");
        ticketB.setStatus(TicketStatus.IN_PROGRESS.toString());

        tickets.add(ticketA);
        tickets.add(ticketB);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testAssignUser(){
        Ticket ticket = new Ticket();
        ticket.setTitle("Ticket User Test");
        ticket.setDescription("Ceci est un ticket test à assigner à un utilisateur");

        User user = new User();
        user.setUsername("user assign test");
        user.setEmail("test@mail.test1");

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
//        when(ticketService.findById(ticket.getId())).thenReturn(ticket);
//        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.assignUser(ticket.getId(), user);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        verify(ticketRepository).findById(ticket.getId());
        verify(ticketRepository).save(ticket);
    }
}
