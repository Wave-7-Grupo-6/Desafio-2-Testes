package com.example.desafio02.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.desafio02.utils.TestUtils.novoComodo;
import static org.assertj.core.api.Assertions.assertThat;

class ComodoTest {

    private Comodo comodo;

    @BeforeEach
    void setUp() {
        comodo = novoComodo();
    }

    @Test
    void getComodoArea_returnArea_quandoSucesso() {

        double areaComodo = comodo.getComprimento() * comodo.getLargura();

        assertThat(comodo.getComodoArea()).isEqualTo(areaComodo);
    }
}