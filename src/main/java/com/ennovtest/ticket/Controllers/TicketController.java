package com.ennovtest.ticket.Controllers;

import com.ennovtest.ticket.DTOs.TicketDto;
import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Services.TicketService;
import com.ennovtest.ticket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id){
        Ticket ticket = ticketService.findById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDto ticketDto){
        Ticket ticket = ticketService.save(ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto){
        Ticket ticket = ticketService.update(id, ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PutMapping("/{id}/assign/{userId}")
    public ResponseEntity<Ticket> assignUser(@PathVariable Long id, @PathVariable Long userId){
        User user = userService.findById(userId);
        Ticket ticket = ticketService.assignUser(id, user);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTickect(@PathVariable Long id){
        ticketService.delete(id);
        return new ResponseEntity<>("Ticket Supprimé", HttpStatus.OK);
    }
}
