package com.projeto.hruser.services;

import com.projeto.hruser.entities.User;
import com.projeto.hruser.repositories.UserRepository;
import com.projeto.hruser.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException("User id:" + id + " não encontrado"));
    }

    public User findByEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(String.format("Email %s não encontrado", email)));
    }
}
