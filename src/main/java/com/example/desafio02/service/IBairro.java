package com.example.desafio02.service;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;

import java.util.List;
import java.util.Optional;

public interface IBairro {
    List<BairroDTO> getTodos();
    List<BairroDTO> salvarBairro(List<Bairro> bairros);
}
