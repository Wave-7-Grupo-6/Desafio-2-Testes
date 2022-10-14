package com.example.desafio02.utils;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static Bairro novoBairro(){
        return new Bairro(1, "Bairro 1", new BigDecimal(25));
    }

//    public static BairroDTO novoBairroDTO(){
//        return new BairroDTO(1, "Bairro 1", new BigDecimal(25));
//    }

}
