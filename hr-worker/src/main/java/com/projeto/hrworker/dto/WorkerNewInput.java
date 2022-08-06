package com.projeto.hrworker.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class WorkerNewInput {

    @NotBlank(message = "O campo name precisa ser preenchido")
    private String name;

    @NotNull(message = "O campo dailyIncome precisa ser preenchido")
    private Double dailyIncome;
}
