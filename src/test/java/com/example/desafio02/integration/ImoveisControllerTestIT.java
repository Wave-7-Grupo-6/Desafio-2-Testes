package com.example.desafio02.integration;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.model.Comodo;
import com.example.desafio02.model.Imovel;
import com.example.desafio02.repository.BairroRepo;
import com.example.desafio02.repository.ImovelRepo;
import com.example.desafio02.service.BairroService;
import com.example.desafio02.service.ImovelService;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.desafio02.utils.TestUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class ImoveisControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ImovelService service;

    @Autowired
    private ImovelRepo repo;

    @BeforeEach
    public void setup() {
        repo.setLinkFile("src/test/resources/imoveis-mock.json");
        log.info("Imóveis cadastrados: " + repo.getTodos().size());
    }

    @AfterEach
    void tearDown() {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File(repo.getLinkFile()), Collections.emptyList());
        } catch (Exception ex) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    @Test
    void salvarImoveis_returnListaImoveis_quandoSucesso() throws Exception {

        mockMvc.perform(
                        post("/imoveis/salvar")
                                .content(mapper.writeValueAsString(novoImovel()))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", CoreMatchers.is(novoImovel().getNome())));
    }

    @ParameterizedTest
    @CsvSource({" , -1", "Teste, -1", "' ',1"})
    void salvarImovel_ThrowsException_quandoArgumentosNaoAtendemCriterios(String nome, int idBairro) throws Exception {
        List<Comodo> comodos = novaListaComodos();

        mockMvc.perform(
                        post("/imoveis/salvar")
                                .content(mapper.writeValueAsString(new Imovel(1, nome,  comodos , idBairro)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Parametros inválidos")));
    }

    //somente para comodos
    @Test
    void salvarImovel_throwsException_quandoNomeComodoInvalido() throws Exception{
        Imovel imovel = novoImovel();
        imovel.setComodos(novaListaComodosNomeInvalido());
        mockMvc.perform(
                        post("/imoveis/salvar")
                                .content(mapper.writeValueAsString(imovel))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Parametros inválidos")));
    }

    @Test
    void salvarImovel_throwsException_quandoComodosLarguraEComprimentoInvalidos() throws Exception{
        Imovel imovel = novoImovel();
        imovel.setComodos(novaListaComodosLarguraEComprimentoInvalidos());
        mockMvc.perform(
                        post("/imoveis/salvar")
                                .content(mapper.writeValueAsString(imovel))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Parametros inválidos")));

    }

    @Test
    void salvarImovel_throwsException_quandoComodosLarguraEComprimentoNegativos() throws Exception{
        Imovel imovel = novoImovel();
        imovel.setComodos(novaListaComodosLarguraEComprimentoNegativos());
        mockMvc.perform(
                        post("/imoveis/salvar")
                                .content(mapper.writeValueAsString(imovel))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Parametros inválidos")));

    }

    @Test
    void getTodos_returnListaImoveis_quandoSucesso() throws Exception {
        repo.salvarImovel(novoImovel());

        mockMvc.perform(
                        get("/imoveis")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", CoreMatchers.is(novoImovel().getNome())));
    }

    @Test
    void getImovelPeloId_returnImovel_quandoSucesso() throws Exception {
        repo.salvarImovel(novoImovel());

        mockMvc.perform(
                        get("/imoveis/pelo_id/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", CoreMatchers.is(novoImovel().getNome())));

    }

    @ParameterizedTest
    @CsvSource({"-5" , "0", "2"})
    void getImovelPeloId_throwNotFound_quandoIdInvalido(int id) throws Exception {
        repo.salvarImovel(novoImovel());

        mockMvc.perform(
                        get("/imoveis/pelo_id/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }
}
