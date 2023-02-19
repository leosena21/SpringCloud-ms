package com.projeto.hrworker.resources;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface WorkerResource {

    @ApiOperation(
            value = "Busca todos os workers",
            httpMethod = "GET",
            response = Worker.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta realizada com sucesso. Pode não retornar workers"),
    })
    ResponseEntity<List<Worker>> findAll();

    @ApiOperation(
            value = "Busca worker a partid do id",
            notes = "Retorna 404 caso exista workers",
            httpMethod = "GET",
            response = Worker.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Worker encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Worker não encontrado")
    })
    ResponseEntity<Worker> findById(@PathVariable Long id);

    @ApiOperation(
            value = "Insere worker",
            httpMethod = "POST",
            response = Worker.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Worker inserido com sucesso"),
    })
    ResponseEntity<Void> insert(@RequestBody @Valid WorkerNewInput workerDto);
}
