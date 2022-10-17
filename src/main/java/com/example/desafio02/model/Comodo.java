package com.example.desafio02.model;

import lombok.AllArgsConstructor;
import com.example.desafio02.dto.ComodoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comodo {
    @NotBlank(message = "O campo não pode ser vazio!")
    @Pattern(regexp = "\\b[A-Z]\\w*\\b")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres!")
    private String nome;

    @NotNull(message = "A largura do cômodo não pode estar vazia!")
    @Max( value = 25, message = "A largura máxima permitida por cômodo é de 25 metros!")
    @Positive
    private Double largura;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio!")
    @Max( value = 33, message = "O comprimento máximo permitida por cômodo é de 33 metros!")
    @Positive
    private Double comprimento;

    public Double getComodoArea(){
        return this.largura * this.comprimento;
    }

}
