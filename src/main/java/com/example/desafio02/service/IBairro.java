package com.example.desafio02.service;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;

import java.util.List;

public interface IBairro {
    List<BairroDTO> getTodos();
    List<BairroDTO> salvarBairro(List<Bairro> bairros);
    BairroDTO getBairroPeloId(int id);
}
