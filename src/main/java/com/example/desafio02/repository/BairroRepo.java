package com.example.desafio02.repository;

import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Bairro;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;

@Repository
public class BairroRepo {
    private String linkFile = "src/main/resources/bairros.json";
    private ObjectMapper mapper = new ObjectMapper();

    public List<Bairro> getTodos(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Bairro[].class));
        }catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return Collections.emptyList();
    }

    public Optional<List<Bairro>> salvarBairro(List<Bairro> novosBairros){
        List<Bairro> bairros = new ArrayList<>(getTodos());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        for(Bairro bairro : novosBairros){
            if(!bairroExistente(bairro)) {
                bairro.setId(generateId());
                bairros.add(bairro);
            }
        }

        try{
            writer.writeValue(new File(linkFile), bairros);

            return Optional.of(novosBairros);
        } catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return Optional.empty();
    }

    public Optional<Bairro> getBairroPeloId(int id){
        if(getTodos().size() < id || id == 0) throw new NotFoundException("Bairro não encontrado");

        return Optional.of(getTodos().get(id - 1));
    }

    public boolean bairroExistente(Bairro bairro){
        return idJaCadastrado(bairro) || nomeJaCadastrado(bairro);
    }

    public boolean idJaCadastrado(Bairro novoBairro) {
        List<Bairro> bairros = new ArrayList<>(getTodos());

        for (Bairro bairro : bairros) {
            if(bairro.getId() == novoBairro.getId()) throw new AlreadyExistingException("Bairro já cadastrado");
        }

        return false;
    }

    public boolean nomeJaCadastrado(Bairro novoBairro){
        List<Bairro> bairros = new ArrayList<>(getTodos());

        for(Bairro bairro : bairros){
            if(bairro.getNome().equals(novoBairro.getNome())) throw new AlreadyExistingException("Bairro já cadastrado");
        }

        return false;
    }

    public int generateId(){
        return getTodos().size() + 1;
    }

    public boolean bairroExistente(int idBairro){
        return getBairroPeloId(idBairro).isPresent();
    }
}
