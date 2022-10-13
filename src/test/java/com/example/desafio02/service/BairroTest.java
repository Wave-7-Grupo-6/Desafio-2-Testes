package com.example.desafio02.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BairroTest {

    @InjectMocks
    private BairroService bairroService;

    @Mock
    private ContaDAO dao;

    private Bairro bairro;

    @BeforeEach
    void setup() {
        bairro = new Bairro();
    }

    @Test
    @DisplayName("Valida se bairro entrada existe existe no repositorio")
    void novoBairro_retornaBairroIncompativel_quandoErro() {
        Mockito.when(dao.novoBairro(ArgumentMatchers.anyString()))
                .thenReturn(bairro);

        Bairro bairro = service.novoBairro(bairro.getNome());

        assertThat(bairro).isNotNull();
    }
}
