package com.projeto.hruser.resources;

import com.projeto.hruser.dto.UserNewInputDTO;
import com.projeto.hruser.entities.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserResource {

    @ApiOperation(
            value = "Busca um usuario a partir do email informado",
            httpMethod = "GET",
            response = User.class
    )
    ResponseEntity<User> findByEmail(@RequestParam String email);

    @ApiOperation(
            value = "Busca um usuario a partir do id informado",
            httpMethod = "GET",
            response = User.class
    )
    ResponseEntity<User> findById(@PathVariable Long id);

    @ApiOperation(
            value = "Insere um usuário",
            httpMethod = "POST",
            response = User.class
    )
    ResponseEntity<Void> insert(@RequestBody UserNewInputDTO userDTO);
}
