package com.ennovtest.ticket.Controllers;

import com.ennovtest.ticket.DTOs.UserDto;
import com.ennovtest.ticket.Entities.Ticket;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Ticket> getUserTickets(@PathVariable Long id){
        User user = userService.findById(id);
        return user.getTickets();
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public  User updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.update(id, userDto);
    }
}
