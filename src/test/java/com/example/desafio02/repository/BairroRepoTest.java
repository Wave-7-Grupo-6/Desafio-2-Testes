package com.example.desafio02.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.desafio02.utils.TestUtils.*;

import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.model.Bairro;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class BairroRepoTest {

    private BairroRepo repo = new BairroRepo();
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        repo.setLinkFile("src/test/resources/bairros-mock.json");
    }

    @AfterEach
    public void tearDown() {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File("src/test/resources/bairros-mock.json"), Collections.emptyList());
        } catch (Exception ex) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    @Test
    void salvarBairro_returnAlreadyExistException_quandoTentaCadastrarDuasVezes() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = Arrays.asList(bairro);
        repo.salvarBairro(bairros);

        assertThrows(AlreadyExistingException.class, () -> repo.salvarBairro(bairros));
    }

}
