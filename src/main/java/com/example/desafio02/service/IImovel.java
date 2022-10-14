package com.example.desafio02.service;

import com.example.desafio02.model.Imovel;

import java.util.List;

public interface IImovel {
    Imovel salvarImovel(Imovel imovel);
    List<Imovel> getTodos();
    Imovel getImovelPeloId(int id);
}
