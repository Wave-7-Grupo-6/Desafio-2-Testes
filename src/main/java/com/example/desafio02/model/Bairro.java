package com.example.desafio02.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class Bairro {
    private int id;

    @NotBlank(message = "O nome do bairro não pode ficar vazio!")
    @Size(max = 45, message = "O comprimento máximo para o nome do bairro é de 30 caracteres!")
    private String nome;

    @NotBlank(message = "O valor não pode ser vazio!")
    @Positive
    @Size(max = 13, message = "Os caracteres do valor não pode passar dos 13 caracteres!")
    private BigDecimal valorMetro;
}
