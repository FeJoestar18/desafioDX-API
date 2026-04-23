package br.com.duxusdesafio.Application.Interfaces.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.duxusdesafio.Application.Dto.CargoDto;
import br.com.duxusdesafio.Application.Dto.CargoInputDto;
import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.IntegranteInputDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Dto.TimeInputDto;

public interface ICadastroService {
    IntegranteDto cadastrarIntegrante(IntegranteInputDto integranteInput);

    TimeDto cadastrarTime(TimeInputDto timeInput);

    IntegranteDto atualizarIntegrante(long id, IntegranteInputDto integranteInput);

    void deletarIntegrante(long id);

    TimeDto atualizarTime(long id, TimeInputDto timeInput);

    void deletarTime(long id);

    TimeDto buscarTimePorId(long id);

    Page<TimeDto> listarTimes(Pageable pageable);

    List<IntegranteDto> listarIntegrantesSemTimes();

    CargoDto cadastrarCargo(CargoInputDto cargoInput);

    List<CargoDto> listarCargos();

    CargoDto atualizarCargo(long id, CargoInputDto cargoInput);

    void deletarCargo(long id);
}
