package br.com.duxusdesafio.WebApi.Controller;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Interfaces.Services.IApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public TimeDto timeDaData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return service.timeDaData(data);
    }

    @ApiOperation("Retorna o integrante que mais apareceu em times no período")
    @GetMapping("/integrante-mais-usado")
    public IntegranteDto integranteMaisUsado(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.integranteMaisUsado(inicio, fim);
    }

    @ApiOperation("Retorna a lista de nomes dos integrantes do time mais recorrente no período")
    @GetMapping("/time-mais-recorrente")
    public List<String> timeMaisRecorrente(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.integrantesDoTimeMaisRecorrente(inicio, fim);
    }

    @ApiOperation("Retorna a função mais comum no período")
    @GetMapping("/funcao-mais-recorrente")
    public String funcaoMaisRecorrente(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.funcaoMaisRecorrente(inicio, fim);
    }

    @ApiOperation("Retorna o clube mais recorrente no período")
    @GetMapping("/clube-mais-recorrente")
    public String clubeMaisRecorrente(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.clubeMaisRecorrente(inicio, fim);
    }

    @ApiOperation("Retorna a contagem de vezes que cada clube apareceu no período")
    @GetMapping("/contagem-clubes")
    public Map<String, Long> contagemClubes(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.contagemDeClubesNoPeriodo(inicio, fim);
    }

    @ApiOperation("Retorna a contagem de vezes que cada função apareceu no período")
    @GetMapping("/contagem-funcoes")
    public Map<String, Long> contagemFuncoes(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.contagemPorFuncao(inicio, fim);
    }
}