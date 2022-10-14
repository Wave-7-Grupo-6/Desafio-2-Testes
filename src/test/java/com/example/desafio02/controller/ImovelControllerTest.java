package com.example.desafio02.controller;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Imovel;
import com.example.desafio02.service.BairroService;
import com.example.desafio02.service.ImovelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.desafio02.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void salvarImovel() throws Exception {
        Imovel imovel = novoImovel();
        BDDMockito.when(service.salvarImovel(any())).thenReturn(imovel);

        ResultActions resposta = mockMvc.perform(post("/imoveis/salvar", imovel)
                .content(mapper.writeValueAsString(imovel))
                .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", CoreMatchers.is(imovel.getNome())))
                .andExpect(jsonPath("$.idBairro", CoreMatchers.is(imovel.getIdBairro())));
    }

    @Test
    void getTodos_returnListaImoveis_quandoSucesso() throws Exception {
        Imovel imovel = novoImovel();

        when(service.getTodos()).thenReturn(Arrays.asList(imovel));

        mockMvc.perform(
                        get("/imoveis")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", CoreMatchers.is(imovel.getNome())));
    }

    @Test
    void getImovelPeloId_returnImovel_quandoSucesso() throws Exception {
        Imovel imovel = novoImovel();

        when(service.getImovelPeloId(anyInt())).thenReturn(imovel);

        mockMvc.perform(
                        get("/imoveis/pelo_id/{id}", imovel.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", CoreMatchers.is(imovel.getNome())))
                .andExpect(jsonPath("$.comodos[0].nome", CoreMatchers.is(imovel.getComodos().get(0).getNome())))
                .andExpect(jsonPath("$.comodos[0].largura", CoreMatchers.is(imovel.getComodos().get(0).getLargura())))
                .andExpect(jsonPath("$.comodos[0].comprimento", CoreMatchers.is(imovel.getComodos().get(0).getComprimento())));

    }

    @Test
    void getImovelPeloId_throwImovelNotFoundException_quandoImovelNaoExiste() throws Exception {
        Integer id = 1;

        when(service.getImovelPeloId(anyInt())).thenThrow(NotFoundException.class);

        mockMvc.perform(
                        get("/imoveis/pelo_id/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Objeto não encontrado")));

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
    @Test
    void getMaiorComodo_returnNomeComodo_quandoSucesso() throws Exception {
        Imovel imovel = novoImovel();

        when(service.getMaiorComodo(anyInt())).thenReturn("sala");

        mockMvc.perform(
                        get("/imoveis/maior_comodo/{id}", imovel.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", CoreMatchers.is("sala")));

    }
}