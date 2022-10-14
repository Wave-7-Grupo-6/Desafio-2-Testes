package com.example.desafio02.service;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.repository.BairroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BairroService implements IBairro{
    @Autowired
    private BairroRepo repo;

    @Override
    public List<BairroDTO> getTodos() {
        return repo.getTodos().stream().map(BairroDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<BairroDTO> salvarBairro(List<Bairro> bairros) throws AlreadyExistingException {
        return repo.salvarBairro(bairros).get().stream().map(BairroDTO::new).collect(Collectors.toList());
    }

    @Override
    public BairroDTO getBairroPeloId(int id) {
        return new BairroDTO(repo.getBairroPeloId(id).get());
    }
}
