package br.com.duxusdesafio.Infrastructure.Service;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Services.IApiService;
import br.com.duxusdesafio.Domain.Entity.Integrante;
import br.com.duxusdesafio.Domain.Entity.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService implements IApiService {

        private final ITimeRepository repository;

        public ApiService(ITimeRepository repository) {
                this.repository = repository;
        }

        /**
         * Vai retornar um Time, com a composição do time daquela data
         */
        @Override
        public TimeDto timeDaData(LocalDate data) {
                return repository.findAll().stream()
                                .filter(t -> t.getData().equals(data))
                                .findFirst()
                                .map(this::toTimeDto)
                                .orElse(null);
        }

        /**
         * Vai retornar o integrante que estiver presente na maior quantidade de times
         * dentro do período
         */
        @Override
        public IntegranteDto integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal) {

                return filtrarPorData(dataInicial, dataFinal).stream()
                                .flatMap(t -> t.getComposicaoTime().stream())
                                .collect(Collectors.groupingBy(
                                                c -> c.getIntegrante(),
                                                Collectors.counting()))
                                .entrySet()
                                .stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .map(this::toIntegranteDto)
                                .orElse(null);
        }

        /**
         * Vai retornar uma lista com os nomes dos integrantes do time mais recorrente
         * dentro do período.
         * OBS: Time é o clube + composição em determinada data
         */
        @Override
        public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal) {

                Map<String, List<Time>> agrupado = filtrarPorData(dataInicial, dataFinal).stream()
                                .collect(Collectors.groupingBy(t -> t.getComposicaoTime().stream()
                                                .map(c -> c.getIntegrante().getNome())
                                                .sorted()
                                                .collect(Collectors.joining(","))));

                return agrupado.entrySet().stream()
                                .max(Comparator.comparingInt(e -> e.getValue().size()))
                                .map(e -> Arrays.asList(e.getKey().split(",")))
                                .orElse(List.of());
        }

        /**
         * Vai retornar a função mais recorrente nos times dentro do período
         */
        @Override
        public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal) {

                return filtrarPorData(dataInicial, dataFinal).stream()
                                .flatMap(t -> t.getComposicaoTime().stream())
                                .collect(Collectors.groupingBy(
                                                c -> c.getIntegrante().getFuncao(),
                                                Collectors.counting()))
                                .entrySet()
                                .stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse(null);
        }

        /**
         * Vai retornar o nome do Clube mais comum dentro do período
         */
        @Override
        public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal) {

                return filtrarPorData(dataInicial, dataFinal).stream()
                                .collect(Collectors.groupingBy(
                                                Time::getNomeDoClube,
                                                Collectors.counting()))
                                .entrySet()
                                .stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse(null);
        }

        /**
         * Vai retornar o número (quantidade) de aparições de cada Clube participante no
         * período
         */
        @Override
        public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal) {

                return filtrarPorData(dataInicial, dataFinal).stream()
                                .collect(Collectors.groupingBy(
                                                Time::getNomeDoClube,
                                                Collectors.counting()));
        }

        /**
         * Vai retornar o número (quantidade) de Funções dentro do período
         */
        @Override
        public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal) {

                return filtrarPorData(dataInicial, dataFinal).stream()
                                .flatMap(t -> t.getComposicaoTime().stream())
                                .collect(Collectors.groupingBy(
                                                c -> c.getIntegrante().getFuncao(),
                                                Collectors.counting()));
        }

        private List<Time> filtrarPorData(LocalDate inicio, LocalDate fim) {
                return repository.findAll().stream()
                                .filter(t -> (inicio == null || !t.getData().isBefore(inicio)))
                                .filter(t -> (fim == null || !t.getData().isAfter(fim)))
                                .toList();
        }

        private TimeDto toTimeDto(Time time) {
                return new TimeDto(
                                time.getId(),
                                time.getNomeDoClube(),
                                time.getData(),
                                time.getComposicaoTime().stream()
                                                .map(c -> toIntegranteDto(c.getIntegrante()))
                                                .toList());
        }

        private IntegranteDto toIntegranteDto(Integrante integrante) {
                return new IntegranteDto(
                                integrante.getId(),
                                integrante.getNome(),
                                integrante.getFuncao());
        }

}
