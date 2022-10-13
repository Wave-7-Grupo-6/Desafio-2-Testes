package com.example.desafio02.service;

import com.example.desafio02.model.Imovel;
import com.example.desafio02.repository.ImovelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImovelService implements IImovel{
    @Autowired
    private ImovelRepo repo;

    @Override
    public Imovel salvarImovel(Imovel imovel) {
        return repo.salvarImovel(imovel).get();
    }

    @Override
    public List<Imovel> getTodos() {
        return repo.getTodos();
    }
}