package com.example.desafio02.controller;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.service.BairroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.example.desafio02.utils.TestUtils.novoBairro;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BairroController.class)
class BairroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BairroService service;

    @Test
    void salvarBairros_returnListaBairrosDTO_quandoSucesso() throws Exception {
        List<Bairro> bairros = Arrays.asList(novoBairro());

        List<BairroDTO> bairrosDTO = Arrays.asList(new BairroDTO(novoBairro()));
        when(service.salvarBairro(anyList())).thenReturn(bairrosDTO);

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
        when(service.salvarBairro(anyList())).thenReturn(bairrosDTO);

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
        List<BairroDTO> bairrosDTO = Arrays.asList(new BairroDTO(novoBairro()));
        when(service.getTodos()).thenReturn(bairrosDTO);

        mockMvc.perform(
                        get("/bairros")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", CoreMatchers.is(novoBairro().getNome())));
    }

    @Test
    void getBairroPeloId_returnBairrosDTO_quandoSucesso() throws Exception {
        Integer bairroId = 1;
        when(service.getBairroPeloId(bairroId)).thenReturn(new BairroDTO(novoBairro()));

        mockMvc.perform(
                        get("/bairros/pelo_id/{id}", bairroId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", CoreMatchers.is(novoBairro().getNome())));

    }
}