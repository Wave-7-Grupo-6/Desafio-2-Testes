package com.example.desafio02.service;

import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Imovel;

import java.math.BigDecimal;
import java.util.List;

public interface IImovel {
    Imovel salvarImovel(Imovel imovel) throws AlreadyExistingException, NotFoundException;
    List<Imovel> getTodos();
    Imovel getImovelPeloId(int id);

    String getImovelArea(int id);

   List<ComodoDTO> getImovelComodosArea(int id);

    BigDecimal getValorImovel(int id);

    String getMaiorComodo(int id);
}
