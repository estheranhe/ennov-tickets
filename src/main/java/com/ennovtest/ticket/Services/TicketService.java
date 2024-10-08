package com.ennovtest.ticket.Services;

import com.ennovtest.ticket.DTOs.TicketDto;
import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Exceptions.NotFoundException;
import com.ennovtest.ticket.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id){
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket non trouvé"));
    }

    public Ticket save(TicketDto ticketDto){
        Ticket ticket = convertDtoToEntity(ticketDto);
        return ticketRepository.save(ticket);
    }

    public Ticket update(Long id, TicketDto ticketDto){
        Ticket ticket = findById(id);
        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setStatus(ticketDto.getTicketStatus().toString());
        return ticketRepository.save(ticket);
    }

    public void delete(Long id){
        ticketRepository.deleteById(id);
    }

    public Ticket assignUser(Long ticketId, User user){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new NotFoundException("Ticket non trouvé"));
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    public Ticket convertDtoToEntity(TicketDto ticketDto){
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        System.out.println(ticketDto.getTicketStatus());
        ticket.setStatus(ticketDto.getTicketStatus().toString());
        return ticket;
    }

}
