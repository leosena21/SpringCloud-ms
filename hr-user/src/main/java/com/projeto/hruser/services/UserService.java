package com.projeto.hruser.services;

import com.projeto.hruser.entities.User;
import com.projeto.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("User " + id + " não encontrado"));
    }

    public User findByEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("email não encontrado"));
    }
}
