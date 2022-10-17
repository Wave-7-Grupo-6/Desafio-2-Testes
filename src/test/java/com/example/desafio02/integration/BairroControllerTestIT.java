package com.example.desafio02.integration;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.repository.BairroRepo;
import com.example.desafio02.service.BairroService;
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

import static com.example.desafio02.utils.TestUtils.novoBairro;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class BairroControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BairroService service;

    @Autowired
    private BairroRepo repo;

    @BeforeEach
    public void setup() {
        repo.setLinkFile("src/test/resources/bairros-mock.json");
        log.info("Bairros cadastrados: " + repo.getTodos().size());
    }

    @AfterEach
    void tearDown() {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File("src/test/resources/bairros-mock.json"), Collections.emptyList());
        } catch (Exception ex) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    @Test
    void salvarBairros_returnListaBairrosDTO_quandoSucesso() throws Exception {
        List<Bairro> bairros = Arrays.asList(novoBairro());

        List<BairroDTO> bairrosDTO = Arrays.asList(new BairroDTO(novoBairro()));

        mockMvc.perform(
                        post("/bairros/salvar")
                                .content(mapper.writeValueAsString(bairros))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].nome", CoreMatchers.is(novoBairro().getNome())));
    }

    @ParameterizedTest
    @CsvSource({" ,-1", "0123456789012345678901234567890,012345678901234"})
    void salvarBairros_ThrowsException_quandoArgumentosNaoAtendemCriterios(String nome, BigDecimal valorMetro) throws Exception {
        List<Bairro> bairros = Arrays.asList(new Bairro(1, nome, valorMetro));

        List<BairroDTO> bairrosDTO = Arrays.asList(new BairroDTO(1, nome, valorMetro));

        mockMvc.perform(
                        post("/bairros/salvar")
                                .content(mapper.writeValueAsString(bairros))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Os campos estão inválidos")));
    }

    @Test
    void getTodos_returnListaBairrosDTO_quandoSucesso() throws Exception {
        repo.salvarBairro(Arrays.asList(novoBairro()));

        List<BairroDTO> bairrosDTO = Arrays.asList(new BairroDTO(novoBairro()));

        mockMvc.perform(
                        get("/bairros")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", CoreMatchers.is(novoBairro().getNome())));
    }

    @Test
    void getBairroPeloId_returnBairrosDTO_quandoSucesso() throws Exception {
        Bairro bairro = repo.salvarBairro(Arrays.asList(novoBairro())).get().get(0);

        mockMvc.perform(
                        get("/bairros/pelo_id/{id}", bairro.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", CoreMatchers.is(novoBairro().getNome())));

    }
}
