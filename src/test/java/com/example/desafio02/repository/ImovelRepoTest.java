package com.example.desafio02.repository;

import com.example.desafio02.exception.AlreadyExistingException;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.model.Imovel;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static com.example.desafio02.utils.TestUtils.novoBairro;
import static com.example.desafio02.utils.TestUtils.novoImovel;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ImovelRepoTest {

    private ImovelRepo repo = new ImovelRepo();

    @BeforeEach
    public void setup() {
        repo.setLinkFile("src/test/resources/imoveis-mock.json");
    }

    @AfterEach
    public void tearDown() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File(repo.getLinkFile()), Collections.emptyList());
        } catch (Exception ex) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    @Test
    void salvarImovel_returnTodos_quandoSucesso() {
        Optional<Imovel> resultadoImovel = repo.salvarImovel(novoImovel());

        assertEquals(novoImovel().getNome(), resultadoImovel.get().getNome());
    }

    @Test
    void salvarImovel_returnAlreadyExistException_quandoTentaCadastrarDuasVezes() {
        Imovel imovel = novoImovel();
        repo.salvarImovel(imovel);

        assertThrows(AlreadyExistingException.class, () -> repo.salvarImovel(imovel));
    }

    @Test
    void getTodos_returnCollecionEmpty_quandoErroNaLeitura() {
        List<Imovel> resultadoImoveis = repo.getTodos();

        assertEquals(Collections.emptyList(), resultadoImoveis);
    }

    @Test
    void getImovelPeloId_returnImovel_quandoSucesso() {
        Imovel imovel = novoImovel();
        repo.salvarImovel(imovel);

        Optional<Imovel> resultadoImovel = repo.getImovelPeloId(1);

        assertEquals(imovel, resultadoImovel.get());
        assertEquals(imovel.getId(), resultadoImovel.get().getId());
    }

    @Test
    void getImovelPeloId_throwsNotFoundException_quandoNaoEncontraId() {
        assertThrows(NotFoundException.class, () -> repo.getImovelPeloId(1));
    }

    @Test
    void nomeJaCadastrado_throwsAlreadyExistingException_quandoJaEstaCadastrado() {
        Imovel imovel = novoImovel();
        repo.salvarImovel(imovel);

        assertThrows(AlreadyExistingException.class, () -> repo.nomeJaCadastrado(imovel));
    }

    @Test
    void nomeJaCadastrado_returnFalse_quandoNaoEstaCadastrado() {
        Imovel imovel = novoImovel();

        assertFalse(repo.nomeJaCadastrado(imovel));
    }

    @Test
    void imovelExistentePorId_retornaTrue_quandoImovelPresente() {
        Imovel imovel = novoImovel();
        repo.salvarImovel(imovel);

        boolean imovelPresente = repo.idJaCadastrado(1);

        assertTrue(imovelPresente);
    }

}
