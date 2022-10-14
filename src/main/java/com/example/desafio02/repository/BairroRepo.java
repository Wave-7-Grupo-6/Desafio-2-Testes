package com.example.desafio02.repository;

import com.example.desafio02.exception.AlreadyRegisteredException;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.util.NumberGenerator;
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
        NumberGenerator numberGenerator = new NumberGenerator(bairros.size());

        for(Bairro bairro : novosBairros){
            if(!bairroExistente(bairro)) {
                bairro.setId(numberGenerator.getNext());
                bairros.add(bairro);
            } else {
                throw new AlreadyRegisteredException("Bairro já cadastrado.");
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
        return Optional.of(getTodos().get(id - 1));
    }

    public boolean bairroExistente(Bairro bairro){
        return getTodos().stream().anyMatch(item -> bairro.getNome().equals(item.getNome()));
    }
}
