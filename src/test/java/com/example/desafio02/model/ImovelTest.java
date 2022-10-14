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

    @Test
    void getComodoArea_returnListComodosDTOcomArea_quandoSucesso() {
        List<ComodoDTO> comodoList = imovel.getComodoArea();

        double areaComodo1 = imovel.getComodos().get(0).getComprimento() * imovel.getComodos().get(0).getLargura();
        double areaComodo2 = imovel.getComodos().get(1).getComprimento() * imovel.getComodos().get(1).getLargura();

        assertThat(comodoList.get(0).getComodoArea()).isEqualTo(areaComodo1);
        assertThat(comodoList.get(0).getNome()).isEqualTo(imovel.getComodos().get(0).getNome());
        assertThat(comodoList.get(1).getComodoArea()).isEqualTo(areaComodo2);
        assertThat(comodoList.get(1).getNome()).isEqualTo(imovel.getComodos().get(1).getNome());
    }
}