package com.ennovtest.ticket.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.TicketStatus;
import com.ennovtest.ticket.Repositories.TicketRepository;
import com.ennovtest.ticket.Services.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testCreateTicket(){
        Ticket ticket = new Ticket();
        ticket.setTitle("Ticket Test 1");
        ticket.setDescription("Ceci est un ticket test");

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket createdTicket = ticketService.save(ticket);

        assertNotNull(createdTicket);
        assertEquals("Ticket Test 1", createdTicket.getTitle());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testFindTickets(){
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticketA = new Ticket();
        ticketA.setTitle("Test Ticket A");
        ticketA.setDescription("Ceci est le ticket test A");
        ticketA.setTicketStatus(TicketStatus.IN_PROGRESS);

        Ticket ticketB = new Ticket();
        ticketB.setTitle("Test Ticket B");
        ticketB.setDescription("Ceci est le ticket test B");
        ticketB.setTicketStatus(TicketStatus.IN_PROGRESS);

        tickets.add(ticketA);
        tickets.add(ticketB);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
    }
}
