package com.projeto.hrworker.services;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import com.projeto.hrworker.repositories.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class WorkerService {

    @Autowired
    private Environment env;

    @Autowired
    WorkerRepository repository;

    public Worker findById(Long id) {
        log.error("PORT = " + env.getProperty("local.server.port"));
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Deu ruim"));
    }

    public List<Worker> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Worker insert(WorkerNewInput workerDto) {
        return repository.save(
                Worker.builder()
                        .name(workerDto.getName())
                        .dailyIncome(workerDto.getDailyIncome())
                .build());
    }
}
