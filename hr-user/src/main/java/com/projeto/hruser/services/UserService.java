package com.projeto.hruser.services;

import com.projeto.hruser.dto.UserNewInputDTO;
import com.projeto.hruser.entities.User;
import com.projeto.hruser.repositories.RoleRepository;
import com.projeto.hruser.repositories.UserRepository;
import com.projeto.hruser.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Setter
    @Value("${role.operator}")
    private String roleOperatorName;

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException("User id:" + id + " não encontrado"));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(String.format("Email %s não encontrado", email)));
    }

    @Transactional
    public User insert(UserNewInputDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .build();

        user.addRole(roleRepository.findByRoleName(roleOperatorName)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Role %s não encontrada", roleOperatorName))));

        return repository.save(user);
    }
}
