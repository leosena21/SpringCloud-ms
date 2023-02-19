package com.projeto.hrworker.services;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import com.projeto.hrworker.repositories.WorkerRepository;
import com.projeto.hrworker.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@RefreshScope
public class WorkerService {

    private Environment env;

    WorkerRepository repository;

    public Worker findById(Long id) {
        log.error("PORT = " + env.getProperty("local.server.port"));
        return repository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException("Worker id:" + id + " não encontrado"));
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
