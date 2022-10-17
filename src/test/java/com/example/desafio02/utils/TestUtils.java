package com.example.desafio02.utils;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.model.Comodo;
import com.example.desafio02.model.Imovel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {

    public static Bairro novoBairro(){
        return new Bairro(1, "Bairro 1", new BigDecimal(25));
    }

    public static Comodo novoComodo(){
        return  new Comodo("sala", 5.0, 5.0);
    }
    public static List<Comodo> novaListaComodos(){
        return Arrays.asList(
                new Comodo("Sala", 5.0, 5.0),
                new Comodo("Quarto", 4.0, 5.0)
        );
    }
    public static List<Comodo> novaListaComodosNomeInvalido(){
        return Arrays.asList(
                new Comodo("sala", 5.0, 5.0),
                new Comodo("quarto", 4.0, 5.0)
        );
    }
    public static List<Comodo> novaListaComodosLarguraEComprimentoInvalidos(){
        return Arrays.asList(
                new Comodo("Sala", 5.0, 5.0),
                new Comodo("Quarto", 27.0, 35.0)
        );
    }
    public static List<Comodo> novaListaComodosLarguraEComprimentoNegativos(){
        return Arrays.asList(
                new Comodo("Sala", -5.0, -5.0),
                new Comodo("Quarto", 4.0, 5.0)
        );
    }

    public static List<ComodoDTO> novaListaComodosDTO(){
        return novaListaComodos().stream().map(ComodoDTO::new).collect(Collectors.toList());
    }

    public static Imovel novoImovel(){
        return new Imovel(1, "Imovel", novaListaComodos(), 1);
    }

}
