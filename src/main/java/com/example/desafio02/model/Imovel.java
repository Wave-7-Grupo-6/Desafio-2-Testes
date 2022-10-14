package com.example.desafio02.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Imovel {
    private int id;
    private String nome;
    private List<Comodo> comodos;
    private int idBairro;

    public void adicionarNovoComodo(Comodo comodo){
        if (comodos == null) comodos = new ArrayList<>();

        comodos.add(comodo);
    }
}
