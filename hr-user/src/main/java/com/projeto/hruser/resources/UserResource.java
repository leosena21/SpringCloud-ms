package com.projeto.hruser.resources;

import com.projeto.hruser.entities.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserResource {

    @ApiOperation(
            value = "Busca um usuario a partir do email informado",
            notes = "Retorna 404 caso não encontre o email cadastrado",
            httpMethod = "GET",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Usuario não encontrado")
    })
    ResponseEntity<User> findByEmail(@RequestParam String email);

    @ApiOperation(
            value = "Busca um usuario a partir do id informado",
            notes = "Retorna 404 caso não encontre o id cadastrado",
            httpMethod = "GET",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Usuario não encontrado")
    })
    ResponseEntity<User> findById(@PathVariable Long id);
}
