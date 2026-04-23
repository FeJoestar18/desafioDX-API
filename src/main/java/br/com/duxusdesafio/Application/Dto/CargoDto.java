package br.com.duxusdesafio.Application.Dto;

import java.util.Objects;

public class CargoDto {

    private long id;
    private String nome;

    public CargoDto() {
    }

    public CargoDto(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CargoDto)) return false;
        CargoDto cargoDto = (CargoDto) o;
        return id == cargoDto.id && Objects.equals(nome, cargoDto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
