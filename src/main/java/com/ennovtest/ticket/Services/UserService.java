package com.ennovtest.ticket.Services;

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

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
