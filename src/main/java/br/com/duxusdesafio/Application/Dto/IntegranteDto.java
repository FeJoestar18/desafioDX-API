package br.com.duxusdesafio.Application.Dto;

import java.util.Objects;

public class IntegranteDto {

    private long id;
    private String nome;
    private String funcao;

    public IntegranteDto() {
    }

    public IntegranteDto(long id, String nome, String funcao) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IntegranteDto))
            return false;
        IntegranteDto that = (IntegranteDto) o;
        return id == that.id &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, funcao);
    }
}
