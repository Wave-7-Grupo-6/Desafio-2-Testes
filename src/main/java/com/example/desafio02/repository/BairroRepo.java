package com.example.desafio02.repository;

import com.example.desafio02.model.Bairro;
import com.example.desafio02.util.NumberGenerator;
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
public class BairroRepo {
    private String linkFile = "src/main/resources/bairros.json";
    private ObjectMapper mapper = new ObjectMapper();
    private NumberGenerator numberGenerator = new NumberGenerator();

    public List<Bairro> getTodos(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Bairro[].class));
        }catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return null;
    }

    public Optional<List<Bairro>> salvarBairro(List<Bairro> novosBairros){
        List<Bairro> bairros = new ArrayList<>(getTodos());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        for(Bairro bairro : novosBairros){
            if(!bairroExistente(bairro)) bairros.add(bairro);
        }

        try{
            writer.writeValue(new File(linkFile), bairros);

            return Optional.of(novosBairros);
        } catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return Optional.empty();
    }

    public boolean bairroExistente(Bairro bairro){
        return getTodos().contains(bairro);
    }
}
