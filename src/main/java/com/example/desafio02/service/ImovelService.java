package com.example.desafio02.service;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Imovel;
import com.example.desafio02.repository.ImovelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

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
        return imovel.getComodos().stream().map(ComodoDTO::new).collect(Collectors.toList());
    }

    public String getMaiorComodo(int id){
        List<ComodoDTO> results = getImovelComodosArea(id);
        Collections.sort(results);
        return results.get(0).getNome();
    }

    public BigDecimal getValorImovel(int id){
        Imovel imovel = getImovelPeloId(id);
        BairroService bairro = new BairroService();
        BairroDTO bairroPorId = bairro.getBairroPeloId(imovel.getIdBairro());
        return bairroPorId.getValorMetro().multiply(BigDecimal.valueOf(imovel.areaTotal()));
    }
}
