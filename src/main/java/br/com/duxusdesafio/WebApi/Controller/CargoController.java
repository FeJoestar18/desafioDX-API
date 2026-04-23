package br.com.duxusdesafio.WebApi.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.Application.Dto.CargoDto;
import br.com.duxusdesafio.Application.Dto.CargoInputDto;
import br.com.duxusdesafio.Application.Interfaces.Services.ICadastroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cargos")
@RestController
@RequestMapping("/api")
public class CargoController {

    private final ICadastroService service;

    public CargoController(ICadastroService service) {
        this.service = service;
    }

    @ApiOperation("Cadastra um novo cargo")
    @PostMapping("/cargos")
    public CargoDto cadastrarCargo(@RequestBody CargoInputDto cargoInput) {
        return service.cadastrarCargo(cargoInput);
    }

    @ApiOperation("Lista todos os cargos")
    @GetMapping("/cargos")
    public List<CargoDto> listarCargos() {
        return service.listarCargos();
    }

    @ApiOperation("Atualiza um cargo existente")
    @PutMapping("/cargos/{id}")
    public CargoDto atualizarCargo(@PathVariable long id, @RequestBody CargoInputDto cargoInput) {
        return service.atualizarCargo(id, cargoInput);
    }

    @ApiOperation("Remove um cargo")
    @DeleteMapping("/cargos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCargo(@PathVariable long id) {
        service.deletarCargo(id);
    }
}
