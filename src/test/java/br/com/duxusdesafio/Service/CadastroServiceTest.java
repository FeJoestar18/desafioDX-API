package br.com.duxusdesafio.Service;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.IntegranteInputDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Dto.TimeInputDto;
import br.com.duxusdesafio.Application.Interfaces.Repository.IComposicaoTimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.IIntegranteRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import br.com.duxusdesafio.Infrastructure.Service.CadastroService;
import br.com.duxusdesafio.Domain.Entity.Integrante;
import br.com.duxusdesafio.Domain.Entity.Cargo;
import br.com.duxusdesafio.Domain.Entity.Time;
import br.com.duxusdesafio.Application.Interfaces.Repository.ICargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CadastroServiceTest {

    @Mock
    private ITimeRepository timeRepository;

    @Mock
    private IIntegranteRepository integranteRepository;

    @Mock
    private IComposicaoTimeRepository composicaoTimeRepository;

    @Mock
    private ICargoRepository cargoRepository;

    @InjectMocks
    private CadastroService cadastroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCadastrarIntegrante() {
        IntegranteInputDto integranteInput = new IntegranteInputDto("Bangalore", 1L);

        Cargo cargo = new Cargo("Atacante");
        cargo.setId(1L);
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        Integrante integrante = new Integrante();
        integrante.setId(1L);
        integrante.setNome("Bangalore");
        integrante.setCargo(cargo);

        when(integranteRepository.save(org.mockito.ArgumentMatchers.any(Integrante.class)))
                .thenReturn(integrante);

        IntegranteDto resultado = cadastroService.cadastrarIntegrante(integranteInput);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Bangalore", resultado.getNome());
        assertEquals("Atacante", resultado.getFuncao());
    }

    @Test
    public void shouldCadastrarTime() {
        TimeInputDto input = new TimeInputDto("Falcons", LocalDate.of(2021, 1, 15), Collections.singletonList(1L));
        Time timeSaved = new Time();
        timeSaved.setId(1L);
        timeSaved.setNomeDoClube("Falcons");
        timeSaved.setData(LocalDate.of(2021, 1, 15));

        Cargo cargo = new Cargo("Atacante");
        cargo.setId(1L);

        Integrante integrante = new Integrante();
        integrante.setId(1L);
        integrante.setNome("Bangalore");
        integrante.setCargo(cargo);

        when(timeRepository.save(org.mockito.ArgumentMatchers.any(Time.class)))
                .thenReturn(timeSaved);
        when(integranteRepository.findById(1L)).thenReturn(Optional.of(integrante));
        when(composicaoTimeRepository.saveAll(org.mockito.ArgumentMatchers.anyList()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TimeDto resultado = cadastroService.cadastrarTime(input);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Falcons", resultado.getNomeDoClube());
        assertEquals(LocalDate.of(2021, 1, 15), resultado.getData());
        assertEquals(1, resultado.getIntegrantes().size());
        assertEquals("Bangalore", resultado.getIntegrantes().get(0).getNome());
    }
}
