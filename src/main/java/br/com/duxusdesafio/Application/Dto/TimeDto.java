package br.com.duxusdesafio.Application.Dto;

import java.time.LocalDate;
import java.util.List;

public class TimeDto {

    private long id;
    private String nomeDoClube;
    private LocalDate data;
    private List<IntegranteDto> integrantes;

    public TimeDto() {
    }

    public TimeDto(long id, String nomeDoClube, LocalDate data, List<IntegranteDto> integrantes) {
        this.id = id;
        this.nomeDoClube = nomeDoClube;
        this.data = data;
        this.integrantes = integrantes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeDoClube() {
        return nomeDoClube;
    }

    public void setNomeDoClube(String nomeDoClube) {
        this.nomeDoClube = nomeDoClube;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<IntegranteDto> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<IntegranteDto> integrantes) {
        this.integrantes = integrantes;
    }
}
