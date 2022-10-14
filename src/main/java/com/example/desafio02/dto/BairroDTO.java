package com.example.desafio02.dto;

import com.example.desafio02.model.Bairro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BairroDTO {
    private int id;
    private String nome;

    public BairroDTO(Bairro bairro) {
        this.id = bairro.getId();
        this.nome = bairro.getNome();
    }
}
