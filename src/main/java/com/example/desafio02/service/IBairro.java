package com.example.desafio02.service;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Bairro;

import java.util.List;

public interface IBairro {
    List<BairroDTO> getTodos();
    List<BairroDTO> salvarBairro(List<Bairro> bairros) throws AlreadyExistingException;
    BairroDTO getBairroPeloId(int id) throws NotFoundException;
}
