package com.example.desafio02.repository;

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

        imoveis.add(novoImovel);

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
}
