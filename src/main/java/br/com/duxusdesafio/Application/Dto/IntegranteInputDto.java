package br.com.duxusdesafio.Application.Dto;

import java.util.Objects;

public class IntegranteInputDto {

    private String nome;
    private Long cargoId;

    public IntegranteInputDto() {
    }

    public IntegranteInputDto(String nome, Long cargoId) {
        this.nome = nome;
        this.cargoId = cargoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IntegranteInputDto))
            return false;
        IntegranteInputDto that = (IntegranteInputDto) o;
        return Objects.equals(nome, that.nome) && Objects.equals(cargoId, that.cargoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cargoId);
    }
}
