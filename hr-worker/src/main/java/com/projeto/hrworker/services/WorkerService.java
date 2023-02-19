package com.projeto.hrworker.services;

import com.projeto.hrworker.dto.WorkerNewInput;
import com.projeto.hrworker.entities.Worker;
import com.projeto.hrworker.repositories.WorkerRepository;
import com.projeto.hrworker.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;
@Slf4j
@AllArgsConstructor
@Service
@RefreshScope
public class WorkerService {

    private Environment env;

    WorkerRepository repository;

    public Worker findById(Long id) {
        log.info(format("Consultando worker id:%s", id));

        log.error("PORT = " + env.getProperty("local.server.port"));
        return repository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException(format("Worker id:%s não encontrado", id)));
    }

    public List<Worker> findAll() {
        log.info(format("Preparando para buscar todos os workers"));
        return repository.findAll();
    }

    @Transactional
    public Worker insert(WorkerNewInput workerDto) {
        log.info(format("Preparando para inserir o worker de nome: %s", workerDto.getName()));
        return repository.save(
                Worker.builder()
                        .name(workerDto.getName())
                        .dailyIncome(workerDto.getDailyIncome())
                .build());
    }
}
