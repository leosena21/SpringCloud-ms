package com.projeto.hrworker.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class WorkerDailyUpdateInput {

    @NotNull(message = "O campo workerId precisa ser preenchido")
    private Long workerId;
    @NotNull(message = "O campo dailyIncome precisa ser preenchido")
    private Double dailyIncome;
}
