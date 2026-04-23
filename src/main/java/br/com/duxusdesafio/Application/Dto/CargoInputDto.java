package br.com.duxusdesafio.Application.Dto;

import java.util.Objects;

public class CargoInputDto {

    private String nome;

    public CargoInputDto() {
    }

    public CargoInputDto(String nome) {
        this.nome = nome;
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
        if (!(o instanceof CargoInputDto)) return false;
        CargoInputDto that = (CargoInputDto) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
