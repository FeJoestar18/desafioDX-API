package br.com.duxusdesafio.Application.Dto;

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
}
