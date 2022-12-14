package com.example.desafio02.repository;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import com.example.desafio02.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.desafio02.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.model.Bairro;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class BairroRepoTest {

    private BairroRepo repo = new BairroRepo();

    @BeforeEach
    public void setup() {
        repo.setLinkFile("src/test/resources/bairros-mock.json");
    }

    @AfterEach
    public void tearDown() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File("src/test/resources/bairros-mock.json"), Collections.emptyList());
        } catch (Exception ex) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    @Test
    void salvarBairro_returnTodos_quandoSucesso() {
        List<Bairro> bairros = new ArrayList<>() {
            {
                add(new Bairro(1, "Terra Firme", new BigDecimal(30)));
                add(new Bairro(2, "Cremação", new BigDecimal(30)));
            }
        };

        Optional<List<Bairro>> resultadoBairros = repo.salvarBairro(bairros);

        assertEquals(bairros, resultadoBairros.get());
        assertEquals(2, resultadoBairros.get().size());
    }

    @Test
    void salvarBairro_returnAlreadyExistException_quandoTentaCadastrarDuasVezes() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = Arrays.asList(bairro);
        repo.salvarBairro(bairros);

        assertThrows(AlreadyExistingException.class, () -> repo.salvarBairro(bairros));
    }

    @Test
    void getTodos_returnCollecionEmpty_quandoErroNaLeitura() {
        repo.setLinkFile("src/test/resources/bairros-mock-erro.json");

        List<Bairro> resultadoBairros = repo.getTodos();

        assertEquals(Collections.emptyList(), resultadoBairros);
    }

    @Test
    void getBairroPeloId_returnBairro_quandoSucesso() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = Arrays.asList(bairro);
        repo.salvarBairro(bairros);

        Optional<Bairro> resultadoBairro = repo.getBairroPeloId(1);

        assertEquals(bairro, resultadoBairro.get());
    }

    @Test
    void getBairroPeloId_throwsNotFoundException_quandoNaoEncontraId() {
        assertThrows(NotFoundException.class, () -> repo.getBairroPeloId(1));
    }

    @Test
    void nomeJaCadastrado_throwsAlreadyExistingException_quandoJaEstaCadastrado() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = Arrays.asList(bairro);
        repo.salvarBairro(bairros);

        assertThrows(AlreadyExistingException.class, () -> repo.nomeJaCadastrado(bairro));
    }

    @Test
    void nomeJaCadastrado_returnFalse_quandoNaoEstaCadastrado() {
        Bairro bairro = novoBairro();

        assertFalse(repo.nomeJaCadastrado(bairro));
    }

    @Test
    void bairroExistentePorId_retornaTrue_quandoBairroPresente() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = List.of(bairro);
        repo.salvarBairro(bairros);

        boolean bairroPresente = repo.bairroExistentePorId(1);

        assertTrue(bairroPresente);
    }

}
