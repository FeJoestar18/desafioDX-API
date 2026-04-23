package br.com.duxusdesafio.WebApi.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Helpers.DateParserHelper;
import br.com.duxusdesafio.Application.Interfaces.Services.IApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "API de Times")
@RestController
@RequestMapping("/api")
public class ApiController {

    private final IApiService service;

    public ApiController(IApiService service) {
        this.service = service;
    }

    @ApiOperation("Retorna o time formado em uma data específica")
    @GetMapping("/time")
    public ResponseEntity<TimeDto> timeDaData(
            @RequestParam String data) {
        LocalDate parsedData = DateParserHelper.parseDate(data);
        TimeDto time = service.timeDaData(parsedData);

        if (time != null) {
            return ResponseEntity.ok(time);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation("Retorna o integrante que mais apareceu em times no período")
    @GetMapping("/integrante-mais-usado")
    public IntegranteDto integranteMaisUsado(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        LocalDate dataInicio = DateParserHelper.parseDate(inicio);
        LocalDate dataFim = DateParserHelper.parseDate(fim);
        return service.integranteMaisUsado(dataInicio, dataFim);
    }

    @ApiOperation("Retorna a lista de nomes dos integrantes do time mais recorrente no período")
    @GetMapping("/time-mais-recorrente")
    public List<String> timeMaisRecorrente(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        LocalDate dataInicio = DateParserHelper.parseDate(inicio);
        LocalDate dataFim = DateParserHelper.parseDate(fim);
        return service.integrantesDoTimeMaisRecorrente(dataInicio, dataFim);
    }

    @ApiOperation("Retorna a função mais comum no período")
    @GetMapping("/funcao-mais-recorrente")
    public String funcaoMaisRecorrente(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        LocalDate dataInicio = DateParserHelper.parseDate(inicio);
        LocalDate dataFim = DateParserHelper.parseDate(fim);
        return service.funcaoMaisRecorrente(dataInicio, dataFim);
    }

    @ApiOperation("Retorna o clube mais recorrente no período")
    @GetMapping("/clube-mais-recorrente")
    public String clubeMaisRecorrente(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        LocalDate dataInicio = DateParserHelper.parseDate(inicio);
        LocalDate dataFim = DateParserHelper.parseDate(fim);
        return service.clubeMaisRecorrente(dataInicio, dataFim);
    }

    @ApiOperation("Retorna a contagem de vezes que cada clube apareceu no período")
    @GetMapping("/contagem-clubes")
    public Map<String, Long> contagemClubes(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        LocalDate dataInicio = DateParserHelper.parseDate(inicio);
        LocalDate dataFim = DateParserHelper.parseDate(fim);
        return service.contagemDeClubesNoPeriodo(dataInicio, dataFim);
    }

    @ApiOperation("Retorna a contagem de vezes que cada função apareceu no período")
    @GetMapping("/contagem-funcoes")
    public Map<String, Long> contagemFuncoes(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        LocalDate dataInicio = DateParserHelper.parseDate(inicio);
        LocalDate dataFim = DateParserHelper.parseDate(fim);
        return service.contagemPorFuncao(dataInicio, dataFim);
    }
}
