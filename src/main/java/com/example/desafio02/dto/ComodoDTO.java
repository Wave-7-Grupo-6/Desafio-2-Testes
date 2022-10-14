package com.example.desafio02.dto;

import com.example.desafio02.model.Comodo;
import lombok.Data;

@Data
public class ComodoDTO {
    String nome;
    Double comprimento;
    Double largura;
    Double comodoArea;

    public ComodoDTO(Comodo comodo){
        this.nome = comodo.getNome();
        this.comodoArea = comodo.getComprimento() * comodo.getLargura();
    }
}
