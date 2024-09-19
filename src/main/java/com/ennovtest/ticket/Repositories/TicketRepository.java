package com.ennovtest.ticket.Repositories;

import com.ennovtest.ticket.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}