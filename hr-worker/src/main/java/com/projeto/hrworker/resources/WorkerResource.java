package com.projeto.hrworker.resources;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import com.projeto.hrworker.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    @Autowired
    private WorkerService service;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/config")
    public ResponseEntity<String> findConfig(){
        return ResponseEntity.ok(service.findConfig());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid WorkerNewInput workerDto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                service.insert(workerDto).getId()
        ).toUri();
        return ResponseEntity.created(uri).build();
    }

}
