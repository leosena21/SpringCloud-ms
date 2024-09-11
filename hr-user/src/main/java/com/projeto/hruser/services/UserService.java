package com.projeto.hruser.services;

import com.projeto.hruser.dto.UserNewInputDTO;
import com.projeto.hruser.entities.User;
import com.projeto.hruser.repositories.RoleRepository;
import com.projeto.hruser.repositories.UserRepository;
import com.projeto.hruser.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Slf4j
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
        log.info(format("Consultando id:%s", id));
        return repository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException(format("User id:%s não encontrado", id)));
    }

    public User findByEmail(String email) {
        log.info(format("Consultando email:%s", email));
        return repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(format("Email %s não encontrado", email)));
    }

    @Transactional
    public User insert(UserNewInputDTO userDTO) {
        log.info(format("Preparando para inserir o usuário de email:%s", userDTO.getEmail()));

        User user = User.builder()
                .name(userDTO.getName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .build();

        user.addRole(roleRepository.findByRoleName(roleOperatorName)
                .orElseThrow(() -> new ObjectNotFoundException(format("Role %s não encontrada", roleOperatorName))));

        return repository.save(user);
    }
}
