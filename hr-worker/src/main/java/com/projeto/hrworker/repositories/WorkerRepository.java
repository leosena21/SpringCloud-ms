package com.projeto.hrworker.repositories;

import com.projeto.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface WorkerRepository extends JpaRepositoryImplementation<Worker, Long> {
}
