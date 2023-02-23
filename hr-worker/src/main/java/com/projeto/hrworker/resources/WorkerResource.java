package com.projeto.hrworker.resources;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface WorkerResource {

    @Operation(
        summary = "Busca todos os workers",
        method = "GET"
            response = Worker.class
    )
    ResponseEntity<List<Worker>> findAll();

    @Operation(
            summary = "Busca worker a partid do id",
            method = "GET"
            httpMethod = "GET",
            response = Worker.class
    )
    ResponseEntity<Worker> findById(@PathVariable Long id);

    @Operation(
        summary = "Insere worker",
        method = "POST"
            response = Worker.class
    )
    ResponseEntity<Void> insert(@RequestBody @Valid WorkerNewInput workerDto);
}
