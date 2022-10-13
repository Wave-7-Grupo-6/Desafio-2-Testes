package com.example.desafio02.dto;

import com.example.desafio02.model.Bairro;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BairroDTO {
    private int id;
    private String nome;

    public BairroDTO(Bairro bairro) {
        this.id = bairro.getId();
        this.nome = bairro.getNome();
    }
}
