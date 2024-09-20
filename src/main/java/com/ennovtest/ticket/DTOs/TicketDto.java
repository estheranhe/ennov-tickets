package com.ennovtest.ticket.DTOs;

import com.ennovtest.ticket.Entities.TicketStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ennovtest.ticket.Entities.Ticket} entity
 */
@Data
public class TicketDto implements Serializable {
    private final String title;
    private final String description;
    private final TicketStatus ticketStatus;
}