package com.example.desafio02.model;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bairro {
    private int id;

    @NotBlank(message = "O nome do bairro não pode ficar vazio!")
    @Size(max = 30, message = "O comprimento máximo para o nome do bairro é de 30 caracteres!")
    private String nome;

    @NotNull(message = "O valor não pode ser vazio!")
    @Positive
    @Digits(integer=13, fraction=2, message = "Os caracteres do valor não pode passar dos 13 dígitos!")
    private BigDecimal valorMetro;
}
