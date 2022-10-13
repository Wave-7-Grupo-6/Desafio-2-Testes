package com.example.desafio02.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ComodoDTO {
    @NotBlank(message = "O campo não pode ser vazio!")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕ][a-záéíóúàèìòùâêîôûãõ]+")
    @Size (max = 30, message = "O comprimento do nome não pode exceder 30 caracteres!")
    private String nome;

    @NotBlank(message = "A largura do cômodo não pode estar vazia!")
    @Max( value = 25, message = "A largura máxima permitida por cômodo é de 25 metros!")
    private Double largura;

    @NotBlank(message = "O comprimento do cômodo não pode estar vazio!")
    @Max( value = 33, message = "O comprimento máximo permitida por cômodo é de 33 metros!")
    private Double comprimento;

}
