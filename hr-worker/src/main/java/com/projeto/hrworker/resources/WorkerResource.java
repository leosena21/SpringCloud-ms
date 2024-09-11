package com.projeto.hrworker.resources;

import com.projeto.hrworker.dto.WorkerDailyUpdateInput;
import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface WorkerResource {

    @Operation(
        summary = "Busca todos os workers",
        method = "GET"
    )
    ResponseEntity<List<Worker>> findAll();

    @Operation(
            summary = "Busca worker a partid do id",
            method = "GET"
    )
    ResponseEntity<Worker> findById(@PathVariable Long id);

    @Operation(
        summary = "Insere worker",
        method = "POST"
    )
    ResponseEntity<Void> insert(@RequestBody @Valid WorkerNewInput workerDto);

    @Operation(
            summary = "Atualiza um worker",
            method = "PUT"
    )
    ResponseEntity<Void> update(@RequestBody @Valid WorkerDailyUpdateInput workerUpdateDto);

    @Operation(
            summary = "Deleta um worker",
            method = "DELETE"
    )
    ResponseEntity<Void> delete(@PathVariable Long id);
}
