package com.example.desafio02.controller;

import com.example.desafio02.service.BairroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BairroController.class)
class BairroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BairroService service;

    @Test
    void salvarBairros_returnSucesso_quando() {
    }

    @Test
    void getTodos() {
    }

    @Test
    void getBairroPeloId() {
    }
}