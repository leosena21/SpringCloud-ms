package com.projeto.hrworker.resources;

import com.projeto.hrworker.dto.WorkerDailyUpdateInput;
import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import com.projeto.hrworker.services.WorkerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/workers")
@Tag(name = "Worker Resource")
public class WorkerResourceImpl implements WorkerResource{

    private WorkerService service;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Worker>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Worker> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> insert(@RequestBody @Valid WorkerNewInput workerDto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                service.insert(workerDto).getId()
        ).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@RequestBody @Valid WorkerDailyUpdateInput workerUpdateDto) {
        service.updateDailyIncome(workerUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteWorker(id);
        return ResponseEntity.noContent().build();
    }

}
