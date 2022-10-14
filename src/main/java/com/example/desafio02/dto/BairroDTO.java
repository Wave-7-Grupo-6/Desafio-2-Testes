package com.example.desafio02.dto;

import com.example.desafio02.model.Bairro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BairroDTO {
    private int id;
    private String nome;
    private BigDecimal valorMetro;

    public BairroDTO(Bairro bairro) {
        this.id = bairro.getId();
        this.nome = bairro.getNome();
        this.valorMetro = bairro.getValorMetro();
    }
}
