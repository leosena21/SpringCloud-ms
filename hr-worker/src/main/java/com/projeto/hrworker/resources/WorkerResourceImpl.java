package com.projeto.hrworker.resources;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import com.projeto.hrworker.services.WorkerService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/workers")
@Api(value = "Worker Resource")
public class WorkerResourceImpl implements WorkerResource{

    private WorkerService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid WorkerNewInput workerDto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                service.insert(workerDto).getId()
        ).toUri();
        return ResponseEntity.created(uri).build();
    }

}
