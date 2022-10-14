package com.example.desafio02.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.repository.BairroRepo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class BairroTest {

    @InjectMocks
    private BairroService service;

    @Mock
    private BairroRepo repo;

    @Test
    @DisplayName("Valida se bairro entrada existe existe no repositorio")
    void novoBairro_retornaBairroIncompativel_quandoErro() {
        BigDecimal valorMetro = new BigDecimal("10.00");

        Bairro bairro = new Bairro(1, "Bairro", valorMetro);
        Bairro bairro2 = new Bairro(2, "Bairro", valorMetro);

        List<Bairro> bairros = new ArrayList<>() {{
            add(bairro);
            add(bairro2);
        }};

        Mockito.when(repo.salvarBairro(bairros)).thenReturn(Optional.of(bairros));

        service.salvarBairro(bairros);
        List<BairroDTO> saidaBairro = service.salvarBairro(bairros);

        assertThat(saidaBairro).isNotNull();
    }
}
