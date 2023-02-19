package com.projeto.hruser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserNewInputDTO {

    @NotBlank(message = "Necessário informar o nome do usuário")
    private String name;

    @NotBlank(message = "Necessário informar o email do usuário")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Necessário informar a senha do usuário")
    @Size(min = 4, message = "Informe uma senha de no minimo 4 caracteres")
    private String password;
}
