package com.ennovtest.ticket.Controllers;

import com.ennovtest.ticket.DTOs.UserDto;
import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getUserTickets(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user.getTickets(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User user = userService.save(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        User user = userService.update(id, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
