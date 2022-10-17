package com.example.desafio02.dto;

import com.example.desafio02.model.Comodo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComodoDTO implements Comparable <ComodoDTO>{
    String nome;
    Double comprimento;
    Double largura;
    Double comodoArea;

    public ComodoDTO(Comodo comodo){
        this.nome = comodo.getNome();
        this.comodoArea = comodo.getComprimento() * comodo.getLargura();
    }

    @Override
    public int compareTo(ComodoDTO comodo) {
        if(this.comodoArea > comodo.getComodoArea())
            return -1;
        if (this.comodoArea < comodo.getComodoArea())
            return 1;
        return 0;
    }

}
