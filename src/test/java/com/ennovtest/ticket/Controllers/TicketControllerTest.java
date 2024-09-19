package com.ennovtest.ticket.Controllers;

import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.TicketStatus;
import com.ennovtest.ticket.Services.TicketService;
import com.ennovtest.ticket.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;


import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private UserService userService;

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticketA = new Ticket();
        ticketA.setTitle("Test Ticket A");
        ticketA.setDescription("Ceci est le ticket test A");
        ticketA.setTicketStatus(TicketStatus.IN_PROGRESS);

        Ticket ticketB = new Ticket();
        ticketB.setTitle("Test Ticket B");
        ticketB.setDescription("Ceci est le ticket test B");
        ticketB.setTicketStatus(TicketStatus.CANCELLED);

        tickets.add(ticketA);
        tickets.add(ticketB);

        when(ticketService.findAll()).thenReturn(tickets);

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Test Ticket A"));

        verify(ticketService, times(1)).findAll();
    }
}
