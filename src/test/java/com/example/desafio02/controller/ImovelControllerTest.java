package com.example.desafio02.controller;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.model.Imovel;
import com.example.desafio02.service.BairroService;
import com.example.desafio02.service.ImovelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.example.desafio02.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImovelController.class)
class ImovelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ImovelService service;

    @Test
    void salvarImovel() {
    }

    @Test
    void getTodos() {
    }

    @Test
    void getImovelPeloId() {
    }

    @Test
    void getImovelArea_returnArea_quandoSucesso() throws Exception {
        Imovel imovel = novoImovel();

        when(service.getImovelArea(anyInt())).thenReturn("45,00");

        mockMvc.perform(
                        get("/imoveis/area/{id}", imovel.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", CoreMatchers.is("45,00")));
    }

    @Test
    void getImovelComodosArea_returnListComodosDTOcomArea_quandoSucesso() throws Exception {
        Imovel imovel = novoImovel();
        double areaComodo1 = imovel.getComodos().get(0).getComprimento() * imovel.getComodos().get(0).getLargura();

        when(service.getImovelComodosArea(imovel.getId())).thenReturn(novaListaComodosDTO());

        mockMvc.perform(
                        get("/imoveis/area_comodos/{id}", imovel.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", CoreMatchers.is(imovel.getComodos().get(0).getNome())))
                .andExpect(jsonPath("$[0].comodoArea", CoreMatchers.is(areaComodo1)));
    }
}