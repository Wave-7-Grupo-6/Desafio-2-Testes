package com.example.desafio02.service;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.exception.NotFoundException;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.repository.BairroRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.desafio02.utils.TestUtils.novoBairro;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BairroServiceTest {

    @Mock
    private BairroRepo repo;
    @InjectMocks
    private BairroService service;

    @Test
    void getTodos_returnListaBairros_quandoSucesso() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = Arrays.asList(bairro);
        when(repo.getTodos()).thenReturn(bairros);

        assertThat(service.getTodos()).isNotEmpty();
        assertThat(service.getTodos().get(0).getNome()).isEqualTo(bairro.getNome());

    }

    @Test
    void salvarBairro_returnListaBairros_quandoSucesso() {
        Bairro bairro = novoBairro();
        List<Bairro> bairros = Arrays.asList(bairro);
        when(repo.salvarBairro(anyList())).thenReturn(Optional.of(bairros));

        assertThat(service.salvarBairro(bairros)).isNotNull();
        assertThat(service.salvarBairro(bairros).get(0).getNome()).isEqualTo(bairro.getNome());
    }

    @Test
    void getBairroPeloId_returnBairro_quandoSucesso() {
        Integer bairroId = 1;
        Bairro bairro = novoBairro();
        when(repo.getBairroPeloId(anyInt())).thenReturn(Optional.of(bairro));

        assertThat(service.getBairroPeloId(bairroId).getNome()).isEqualTo(bairro.getNome());
    }

    @Test
    void getBairroPeloId_throwsNotFoundException_quandoBairroNaoExiste() {
        Integer bairroId = 1;
        Bairro bairro = novoBairro();
        when(repo.getBairroPeloId(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> service.getBairroPeloId(bairroId));
    }

}