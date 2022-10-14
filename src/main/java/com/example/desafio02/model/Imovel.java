package com.example.desafio02.model;

import com.example.desafio02.dto.ComodoDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public double areaTotal(){
        double areaTotal = 0;
        for(Comodo comodo: comodos){
            double area = comodo.getLargura() * comodo.getComprimento();
            areaTotal = areaTotal + area;
        }
        return areaTotal;
    }

    public List<ComodoDTO> getComodoArea(){
        List<ComodoDTO> listaComodos = new ArrayList<ComodoDTO>();
        for(Comodo comodo: comodos) listaComodos.add(new ComodoDTO(comodo));
        return listaComodos;
    }
}
