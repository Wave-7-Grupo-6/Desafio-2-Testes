package com.example.desafio02.repository;

import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.model.Imovel;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ImovelRepo {
    private final String linkFile = "src/main/resources/imoveis.json";
    private ObjectMapper mapper = new ObjectMapper();

    public Optional<Imovel> salvarImovel(Imovel novoImovel){
        List<Imovel> imoveis = new ArrayList<>(getTodos());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        if(!bairroExiste(novoImovel.getIdBairro())) throw new NotFoundException("Bairro não encontrado");
        if(!imovelExistente(novoImovel)) {
            novoImovel.setId(generateId());
            imoveis.add(novoImovel);
        }

        try{
            writer.writeValue(new File(linkFile), imoveis);

            return Optional.of(novoImovel);
        } catch (Exception ex){
            System.out.println("Erro ao salvar os dados");
        }

        return Optional.empty();
    }

    public List<Imovel> getTodos(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Imovel[].class));
        } catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return null;
    }

    public Optional<Imovel> getImovelPeloId(int id){
        List<Imovel> imoveis = new ArrayList<>(getTodos());

        for (Imovel imovel : imoveis) {
            if(imovel.getId() == id) return Optional.of(imovel);
        }

        throw new NotFoundException("Imovel não encontrado");
    }

    public boolean imovelExistente(Imovel imovel){
        return idJaCadastrado(imovel) || nomeJaCadastrado(imovel);
    }

    public boolean idJaCadastrado(Imovel novoImovel) {
        List<Imovel> imoveis = new ArrayList<>(getTodos());

        for (Imovel imovel : imoveis) {
            if(imovel.getId() == novoImovel.getId()) throw new AlreadyExistingException("Imovel já cadastrado");
        }

        return false;
    }

    public boolean nomeJaCadastrado(Imovel novoImovel){
        List<Imovel> imoveis = new ArrayList<>(getTodos());

        for(Imovel imovel : imoveis){
            if(imovel.getNome().equals(novoImovel.getNome())) throw new AlreadyExistingException("Imovel já cadastrado");
        }

        return false;
    }

    public int generateId(){
        return getTodos().size() + 1;
    }

    public boolean bairroExiste(int idBairro){
        BairroRepo repo = new BairroRepo();

        return repo.bairroExistente(idBairro);
    }
}