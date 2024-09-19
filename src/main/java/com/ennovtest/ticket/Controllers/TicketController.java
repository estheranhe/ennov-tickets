package com.ennovtest.ticket.Controllers;

import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Services.TicketService;
import com.ennovtest.ticket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id){
        return ticketService.findById(id);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketService.save(ticket);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket){
        Ticket findTicket = ticketService.findById(id);
        findTicket.setTitle(ticket.getTitle());
        findTicket.setDescription(ticket.getDescription());
        findTicket.setTicketStatus(ticket.getTicketStatus());
        return ticketService.save(findTicket);
    }

    @PutMapping("/{id}/assign/{userId}")
    public Ticket assignUser(@PathVariable Long id, @PathVariable Long userId){
        User user = userService.findById(userId);
        return ticketService.assignUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteTickect(@PathVariable Long id){
        ticketService.delete(id);
    }
}
