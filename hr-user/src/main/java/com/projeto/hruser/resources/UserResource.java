package com.projeto.hruser.resources;

import com.projeto.hruser.dto.UserNewInputDTO;
import com.projeto.hruser.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserResource {

    @Operation(
            summary  = "Busca um usuario a partir do email informado",
            method = "GET"
    )
    ResponseEntity<User> findByEmail(@RequestParam String email);

    @Operation(
            summary = "Busca um usuario a partir do id informado",
            method = "GET"
    )
    ResponseEntity<User> findById(@PathVariable Long id);

    @Operation(
            summary = "Insere um usuário",
            method = "POST"
    )
    ResponseEntity<Void> insert(@RequestBody UserNewInputDTO userDTO);
}
