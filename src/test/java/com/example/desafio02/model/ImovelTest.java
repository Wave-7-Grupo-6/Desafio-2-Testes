package com.example.desafio02.model;

import com.example.desafio02.dto.ComodoDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.desafio02.utils.TestUtils.novoImovel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ImovelTest {

    private Imovel imovel;

    @BeforeEach
    void setUp() {
        imovel = novoImovel();
    }

    @Test
    void areaTotal_returnValorTotal_quandoSucesso() {
    }

}