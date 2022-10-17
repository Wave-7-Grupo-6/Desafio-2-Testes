package com.example.desafio02.model;

import com.example.desafio02.dto.ComodoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imovel {
    private int id;

    @NotBlank(message = "O nome do imóvel não pode estar em branco")
    @NotEmpty(message = "O nome do imóvel não pode estar vazio")
    @NotNull(message = "O nome do imóvel não pode ser nulo")
    private String nome;

    @NotEmpty(message = "A lista de comodos não pode estar vazia")
    private List<@Valid Comodo> comodos;

    @Min(value = 1, message = "O id do bairro não pode ser menor que 1")
    private int idBairro;

    public double areaTotal(){
        double areaTotal = 0;
        for(Comodo comodo: comodos){
            double area = comodo.getLargura() * comodo.getComprimento();
            areaTotal = areaTotal + area;
        }
        return areaTotal;
    }
}
