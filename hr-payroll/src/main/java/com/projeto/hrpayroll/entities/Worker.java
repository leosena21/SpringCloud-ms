package com.projeto.hrpayroll.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Worker implements Serializable {
    private static final long serialVersionUID = 3595999533149861710L;

    private Long id;
    private String name;
    private Double dailyIncome;
}
