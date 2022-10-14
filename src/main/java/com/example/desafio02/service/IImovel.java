package com.example.desafio02.service;

import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.model.Imovel;

import java.util.List;

public interface IImovel {
    Imovel salvarImovel(Imovel imovel);
    List<Imovel> getTodos();
    Imovel getImovelPeloId(int id);

    Double getImovelArea(int id);

    List<ComodoDTO> getImovelComodosArea(int id);
}
