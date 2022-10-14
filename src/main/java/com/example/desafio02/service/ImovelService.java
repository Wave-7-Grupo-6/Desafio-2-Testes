package com.example.desafio02.service;

import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
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
    public Imovel salvarImovel(Imovel imovel) throws AlreadyExistingException, NotFoundException {
        return repo.salvarImovel(imovel).get();
    }

    @Override
    public List<Imovel> getTodos() {
        return repo.getTodos();
    }

    @Override
    public Imovel getImovelPeloId(int id) {
        return repo.getImovelPeloId(id).get();
    }

    public Double getImovelArea(int id){
        Imovel imovel = getImovelPeloId(id);
        return imovel.areaTotal();
    }

    public List<ComodoDTO> getImovelComodosArea(int id){
        Imovel imovel = getImovelPeloId(id);
        return imovel.getComodoArea();
    }
}
