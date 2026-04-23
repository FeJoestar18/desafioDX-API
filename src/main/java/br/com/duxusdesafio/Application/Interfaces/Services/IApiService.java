package br.com.duxusdesafio.Application.Interfaces.Services;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IApiService {
    TimeDto timeDaData(LocalDate data);

    IntegranteDto integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal);

    List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal);

    String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal);

    String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal);

    Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal);

    Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal);
}
