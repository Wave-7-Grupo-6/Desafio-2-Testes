package com.example.desafio02.model;

import com.example.desafio02.dto.ComodoDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class Imovel {
    private int id;

    @NotBlank(message = "O nome do imóvel não pode estar em branco")
    private String nome;

    @NotEmpty(message = "A lista de comodos não pode estar vazia")
    private List<@Valid Comodo> comodos;

    @Min(value = 1, message = "O id do bairro não pode ser menor que 1")
    private int idBairro;

//    public void adicionarNovoComodo(Comodo comodo){
//        if (comodos == null) comodos = new ArrayList<>();
//
//        comodos.add(comodo);
//    }

    public double areaTotal(){
        double areaTotal = 0;
        for(Comodo comodo: comodos){
            double area = comodo.getLargura() * comodo.getComprimento();
            areaTotal = areaTotal + area;
        }
        return areaTotal;
    }

//    public List<ComodoDTO> getComodoArea(){
//        List<ComodoDTO> listaComodos = new ArrayList<ComodoDTO>();
//        for(Comodo comodo: comodos) listaComodos.add(new ComodoDTO(comodo));
//        return listaComodos;
//    }

}
