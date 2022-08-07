package com.projeto.hrpayroll.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {
    private static final long serialVersionUID = 2235480630778822947L;

    private String name;
    private Double dailyIncome;
    private Integer days;

    public Double getTotal(){
        return dailyIncome*days;
    }

}
