package com.ennovtest.ticket.Services;

import com.ennovtest.ticket.DTOs.UserDto;
import com.ennovtest.ticket.Entities.User;
import com.ennovtest.ticket.Exceptions.NotFoundException;
import com.ennovtest.ticket.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
    }

    public User save(UserDto userDto){
        User user = convertUserDtoToEntity(userDto);
        return userRepository.save(user);
    }

    public User update(Long id, UserDto userDto){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User convertUserDtoToEntity(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        return user;
    }

}
