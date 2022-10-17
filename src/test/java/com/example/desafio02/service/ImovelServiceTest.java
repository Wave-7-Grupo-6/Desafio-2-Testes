package com.example.desafio02.service;

import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.model.Comodo;
import com.example.desafio02.model.Imovel;
import com.example.desafio02.repository.ImovelRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.desafio02.utils.TestUtils.novoImovel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImovelServiceTest {

    @Mock
    private ImovelRepo repo;
    @InjectMocks
    private ImovelService service;

    @Test
    void salvarImovel_returnImovel_quandoSucesso() {
        Imovel imovel = novoImovel();
        when(repo.salvarImovel(imovel)).thenReturn(Optional.of(imovel));

        Imovel imovelSalvo = service.salvarImovel(novoImovel());

        assertThat(imovelSalvo.getNome()).isEqualTo(imovel.getNome());
        assertThat(imovelSalvo.getComodos()).isEqualTo(imovel.getComodos());
        assertThat(imovelSalvo.getIdBairro()).isEqualTo(imovel.getIdBairro());
    }

    @Test
    void getTodos_returnListaImoveis_quandoSucesso() {
        List<Imovel> imoveis = Arrays.asList(novoImovel());
        when(repo.getTodos()).thenReturn(imoveis);

        assertThat(service.getTodos()).isEqualTo(imoveis);
    }

    @Test
    void getImovelPeloId_returnImovel_quandoSucesso() {
        Imovel imovel = novoImovel();
        when(repo.getImovelPeloId(anyInt())).thenReturn(Optional.of(imovel));

        assertThat(service.getImovelPeloId(imovel.getId())).isEqualTo(imovel);
    }

    @Test
    void getImovelArea_returnArea_quandoSucesso() {
        Imovel imovel = novoImovel();
        when(repo.getImovelPeloId(anyInt())).thenReturn(Optional.of(imovel));

        assertThat(service.getImovelArea(imovel.getId())).isEqualTo("45,00");
    }

    @Test
    void getImovelComodosArea_returnListComodosDTOcomArea_quandoSucesso() {
        Integer id = 1;
        Imovel imovel = novoImovel();
        double areaComodo1 = imovel.getComodos().get(0).getComprimento() * imovel.getComodos().get(0).getLargura();

        when(repo.getImovelPeloId(id)).thenReturn(Optional.of(imovel));

        List<ComodoDTO> comodoDTOS = service.getImovelComodosArea(id);
        assertThat(comodoDTOS).isNotEmpty();
        assertThat(comodoDTOS.get(0).getNome()).isEqualTo(imovel.getComodos().get(0).getNome());
        assertThat(comodoDTOS.get(0).getComodoArea()).isEqualTo(areaComodo1);
    }

    @Test
    void getMaiorComodo_returnNomeComodo_quandoSucesso(){
        Imovel imovel = novoImovel();

        when(repo.getImovelPeloId(imovel.getId())).thenReturn(Optional.of(imovel));

        assertThat(service.getMaiorComodo(imovel.getId())).isEqualTo("Sala");
    }
}