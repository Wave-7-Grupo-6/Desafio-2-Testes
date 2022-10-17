package com.example.desafio02.model;

import com.example.desafio02.dto.ComodoDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

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
        Double area = 0.0;

        for (int i = 0; i < imovel.getComodos().size(); i++) {
            area += (imovel.getComodos().get(i).getLargura() * imovel.getComodos().get(i).getComprimento());
        }

        assertThat(imovel.areaTotal()).isEqualTo(area);

    }

}